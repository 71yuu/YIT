package com.yuu.yit.education.user.service.biz.auth;

import com.xiaoleilu.hutool.util.ObjectUtil;
import com.yuu.yit.education.user.service.common.bo.auth.AuthLecturerProfitPageBO;
import com.yuu.yit.education.user.service.common.bo.auth.AuthLecturerProfitSaveBO;
import com.yuu.yit.education.user.service.common.dto.auth.AuthLecturerProfitPageDTO;
import com.yuu.yit.education.user.service.dao.LecturerDao;
import com.yuu.yit.education.user.service.dao.LecturerExtDao;
import com.yuu.yit.education.user.service.dao.LecturerProfitDao;
import com.yuu.yit.education.user.service.dao.UserExtDao;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.*;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.LecturerProfitExample.Criteria;
import com.yuu.yit.education.util.base.BaseBiz;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.base.Result;
import com.yuu.yit.education.util.enums.ProfitStatusEnum;
import com.yuu.yit.education.util.enums.StatusIdEnum;
import com.yuu.yit.education.util.tools.BeanUtil;
import com.yuu.yit.education.util.tools.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * 讲师提现日志表
 *
 * @author Yuu
 */
@Component
public class AuthApiLecturerProfitBiz extends BaseBiz {

	@Autowired
	private LecturerProfitDao lecturerProfitDao;
	@Autowired
	private LecturerExtDao lecturerExtDao;
	@Autowired
	private LecturerDao lecturerDao;
	@Autowired
	private UserExtDao userExtDao;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 讲师提现记录分页列出接口
	 * 
	 * @param authLecturerProfitPageBO
	 * @author Yuu
	 */
	public Result<Page<AuthLecturerProfitPageDTO>> list(AuthLecturerProfitPageBO authLecturerProfitPageBO) {
		if (authLecturerProfitPageBO.getLecturerUserNo() == null) {
			return Result.error("lecturerUserNo不能为空");
		}
		LecturerProfitExample example = new LecturerProfitExample();
		Criteria c = example.createCriteria();
		c.andLecturerUserNoEqualTo(authLecturerProfitPageBO.getLecturerUserNo());
		example.setOrderByClause(" id desc ");
		Page<LecturerProfit> page = lecturerProfitDao.listForPage(authLecturerProfitPageBO.getPageCurrent(), authLecturerProfitPageBO.getPageSize(), example);
		Page<AuthLecturerProfitPageDTO> dtoPage = PageUtil.transform(page, AuthLecturerProfitPageDTO.class);
		for (AuthLecturerProfitPageDTO dto : dtoPage.getList()) {
			if (!StringUtils.isEmpty(dto.getBankCardNo())) {
				dto.setBankCardNo(dto.getBankCardNo().substring(0, 6) + "****" + dto.getBankCardNo().substring(12, dto.getBankCardNo().length()));
			}
		}
		return Result.success(dtoPage);
	}

	/**
	 * 讲师申请提现接口
	 * 
	 * @param authLecturerProfitSaveBO
	 * @author Yuu
	 */
	@Transactional
	public Result<Integer> save(AuthLecturerProfitSaveBO authLecturerProfitSaveBO) {
		// 查询用户信息
		UserExt userExt = userExtDao.getByUserNo(authLecturerProfitSaveBO.getLecturerUserNo());
		if (ObjectUtil.isNull(userExt) || StatusIdEnum.NO.getCode().equals(userExt.getStatusId())) {
			return Result.error("该用户不存在");
		}
		// 查询讲师信息
		Lecturer lecturer = lecturerDao.getByLecturerUserNo(authLecturerProfitSaveBO.getLecturerUserNo());
		if (ObjectUtil.isNull(lecturer) || StatusIdEnum.NO.getCode().equals(lecturer.getStatusId())) {
			return Result.error("该讲师不存在");
		}
		// 查询用户账户信息
		LecturerExt lecturerExt = lecturerExtDao.getByLecturerUserNo(authLecturerProfitSaveBO.getLecturerUserNo());
		if (ObjectUtil.isNull(lecturerExt) || StatusIdEnum.NO.getCode().equals(lecturerExt.getStatusId())) {
			return Result.error("该账户不存在");
		}
		if (lecturerExt.getEnableBalances().compareTo(authLecturerProfitSaveBO.getExtractMoney()) < 0) {
			return Result.error("账户余额不足");
		}
		if (StringUtils.isEmpty(lecturerExt.getBankCardNo())) {
			return Result.error("银行卡未绑定");
		}

		String sign = SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances());
		if (sign.equals(lecturerExt.getSign())) {
			// 先存日志，再操作
			LecturerProfit lecturerProfitInfo = BeanUtil.copyProperties(lecturerExt, LecturerProfit.class);
			lecturerProfitInfo.setGmtCreate(null);
			lecturerProfitInfo.setProfitStatus(ProfitStatusEnum.CONFIRMING.getCode());

			// 讲师收入
			BigDecimal lecturerProfit = authLecturerProfitSaveBO.getExtractMoney().multiply(lecturer.getLecturerProportion());
			// 平台收入
			BigDecimal platformProfit = authLecturerProfitSaveBO.getExtractMoney().subtract(lecturerProfit);

			lecturerProfitInfo.setLecturerProfit(lecturerProfit);// 讲师收入 = 提现金额 * 讲师分润比例
			lecturerProfitInfo.setPlatformProfit(platformProfit);// 平台收入 = 提现金额 - 讲师收入
			lecturerProfitDao.save(lecturerProfitInfo);

			// 减少账户可提现金额,增加账号冻结金额,生成新的签名防篡改
			lecturerExt.setEnableBalances(lecturerExt.getEnableBalances().subtract(authLecturerProfitSaveBO.getExtractMoney()));// 账户可提现金额减去申请的金额
			lecturerExt.setFreezeBalances(lecturerExt.getFreezeBalances().add(authLecturerProfitSaveBO.getExtractMoney()));// 账号冻结金额加上申请的金额
			lecturerExt.setSign(SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances()));
			return Result.success(lecturerExtDao.updateById(lecturerExt));
		} else {
			logger.error("签名为：{}，{}", sign, lecturerExt.getSign());
			return Result.error("账户异常，请联系客服");
		}
	}

	/**
	 * 讲师可提现余额查询接口
	 *
	 * @param lecturerUserNo
	 * @return
	 */
	public Result<BigDecimal> enableBalances(Long lecturerUserNo) {
		// 查询用户信息
		UserExt userExt = userExtDao.getByUserNo(lecturerUserNo);
		if (ObjectUtil.isNull(userExt) || StatusIdEnum.NO.getCode().equals(userExt.getStatusId())) {
			return Result.error("该用户不存在");
		}
		// 查询讲师信息
		Lecturer lecturer = lecturerDao.getByLecturerUserNo(lecturerUserNo);
		if (ObjectUtil.isNull(lecturer) || StatusIdEnum.NO.getCode().equals(lecturer.getStatusId())) {
			return Result.error("该讲师不存在");
		}
		// 查询用户账户信息
		LecturerExt lecturerExt = lecturerExtDao.getByLecturerUserNo(lecturerUserNo);
		if (ObjectUtil.isNull(lecturerExt) || StatusIdEnum.NO.getCode().equals(lecturerExt.getStatusId())) {
			return Result.error("该账户不存在");
		}
		LecturerExt byLecturerUserNo = lecturerExtDao.getByLecturerUserNo(lecturerUserNo);
		return Result.success(byLecturerUserNo.getEnableBalances());
	}
}
