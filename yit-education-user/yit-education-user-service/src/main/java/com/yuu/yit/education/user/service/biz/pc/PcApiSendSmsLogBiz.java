package com.yuu.yit.education.user.service.biz.pc;

import com.aliyuncs.exceptions.ClientException;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.yuu.yit.education.system.common.bean.vo.SysVO;
import com.yuu.yit.education.system.common.interfaces.BossSys;
import com.yuu.yit.education.user.service.common.req.SendSmsLogPageREQ;
import com.yuu.yit.education.user.service.common.req.SendSmsLogSendREQ;
import com.yuu.yit.education.user.service.common.resq.SendSmsLogPageRESQ;
import com.yuu.yit.education.user.service.dao.PlatformDao;
import com.yuu.yit.education.user.service.dao.SendSmsLogDao;
import com.yuu.yit.education.user.service.dao.UserDao;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.SendSmsLog;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.SendSmsLogExample;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.SendSmsLogExample.Criteria;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.User;
import com.yuu.yit.education.util.aliyun.Aliyun;
import com.yuu.yit.education.util.aliyun.AliyunUtil;
import com.yuu.yit.education.util.base.BaseBiz;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.base.Result;
import com.yuu.yit.education.util.enums.IsSuccessEnum;
import com.yuu.yit.education.util.enums.ResultEnum;
import com.yuu.yit.education.util.tools.BeanUtil;
import com.yuu.yit.education.util.tools.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Component
public class PcApiSendSmsLogBiz extends BaseBiz {

	private static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199)\\d{8}$";// 手机号码校验

	@Autowired
	private BossSys bossSys;

	@Autowired
	private SendSmsLogDao dao;
	@Autowired
	private PlatformDao platformDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public Result<Page<SendSmsLogPageRESQ>> listForPage(SendSmsLogPageREQ req) {
		SendSmsLogExample example = new SendSmsLogExample();
		Criteria c = example.createCriteria();
		if (StringUtils.hasText(req.getMobile())) {
			c.andMobileEqualTo(req.getMobile());
		}
		if (StringUtils.hasText(req.getBeginGmtCreate())) {
			c.andGmtCreateGreaterThanOrEqualTo(DateUtil.parseDate(req.getBeginGmtCreate(), "yyyy-MM-dd"));
		}
		if (StringUtils.hasText(req.getEndGmtCreate())) {
			c.andGmtCreateLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(req.getEndGmtCreate(), "yyyy-MM-dd"), 1));
		}
		example.setOrderByClause(" id desc ");
		Page<SendSmsLog> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, SendSmsLogPageRESQ.class));
	}

	public Result<Integer> send(SendSmsLogSendREQ req) {
		if (StringUtils.isEmpty(req.getMobile())) {
			return Result.error("手机号不能为空");
		}
		// 手机号码校验
		if (!Pattern.compile(REGEX_MOBILE).matcher(req.getMobile()).matches()) {
			return Result.error("手机号码格式不正确");
		}
		User user = userDao.getByMobile(req.getMobile());
		if (ObjectUtil.isNull(user)) {
			return Result.error("用户不存在");
		}

		SysVO sys = bossSys.getSys();
		if (ObjectUtil.isNull(sys)) {
			return Result.error("未配置系统配置表");
		}
		if (StringUtils.isEmpty(sys.getAliyunAccessKeyId()) || StringUtils.isEmpty(sys.getAliyunAccessKeySecret())) {
			return Result.error("aliyunAccessKeyId或aliyunAccessKeySecret未配置");
		}
		if (StringUtils.isEmpty(sys.getSmsCode()) || StringUtils.isEmpty(sys.getSignName())) {
			return Result.error("smsCode或signName未配置");
		}
		// 创建日志实例
		SendSmsLog sendSmsLog = new SendSmsLog();
		sendSmsLog.setMobile(req.getMobile());
		sendSmsLog.setTemplate(sys.getSmsCode());
		// 随机生成验证码
		sendSmsLog.setSmsCode(RandomUtil.randomNumbers(6));
		try {
			// 发送验证码
			boolean result = AliyunUtil.sendMsg(req.getMobile(), sendSmsLog.getSmsCode(), BeanUtil.copyProperties(sys, Aliyun.class));
			if (result) {
				// 成功发送，验证码存入缓存：5分钟有效
				redisTemplate.opsForValue().set(req.getMobile(), sendSmsLog.getSmsCode(), 5, TimeUnit.MINUTES);
				sendSmsLog.setIsSuccess(IsSuccessEnum.SUCCESS.getCode());
				int results = dao.save(sendSmsLog);
				if (results > 0) {
					return Result.success(results);
				}
				return Result.error(ResultEnum.USER_SEND_FAIL);
			}
			// 发送失败
			sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
			dao.save(sendSmsLog);
			return Result.error(ResultEnum.USER_SEND_FAIL);
		} catch (ClientException e) {
			sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
			dao.save(sendSmsLog);
			logger.error("发送失败，原因={}", e);
			return Result.error(ResultEnum.USER_SEND_FAIL);
		}
	}

}
