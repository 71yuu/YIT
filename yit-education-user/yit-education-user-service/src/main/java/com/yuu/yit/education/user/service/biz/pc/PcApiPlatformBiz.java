package com.yuu.yit.education.user.service.biz.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.yuu.yit.education.user.service.common.req.PlatformDeleteREQ;
import com.yuu.yit.education.user.service.common.req.PlatformPageREQ;
import com.yuu.yit.education.user.service.common.req.PlatformSaveREQ;
import com.yuu.yit.education.user.service.common.req.PlatformUpdateREQ;
import com.yuu.yit.education.user.service.common.req.PlatformViewREQ;
import com.yuu.yit.education.user.service.common.resq.PlatformPageRESQ;
import com.yuu.yit.education.user.service.common.resq.PlatformViewRESQ;
import com.yuu.yit.education.user.service.dao.PlatformDao;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.Platform;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.PlatformExample;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.PlatformExample.Criteria;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.base.Result;
import com.yuu.yit.education.util.enums.ResultEnum;
import com.yuu.yit.education.util.tools.BeanUtil;
import com.yuu.yit.education.util.tools.StrUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 平台信息
 */
@Component
public class PcApiPlatformBiz {

	@Autowired
	private PlatformDao dao;

	public Result<Page<PlatformPageRESQ>> listForPage(PlatformPageREQ req) {
		PlatformExample example = new PlatformExample();
		Criteria c = example.createCriteria();
		if (StringUtils.hasText(req.getClientName())) {
			c.andClientNameLike(PageUtil.rightLike(req.getClientName()));
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<Platform> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, PlatformPageRESQ.class));
	}

	public Result<Integer> save(PlatformSaveREQ req) {
		if (StringUtils.isEmpty(req.getClientName())) {
			return Result.error("客户端名称不能为空");
		}
		Platform platform = dao.getByClientName(req.getClientName());
		if (ObjectUtil.isNotNull(platform)) {
			return Result.error("客户端名称已添加");
		}
		Platform record = BeanUtil.copyProperties(req, Platform.class);
		record.setClientId("lk" + StrUtil.get32UUID());
		record.setClientSecret(StrUtil.get32UUID());
		int results = dao.save(record);
		if (results < 0) {
			return Result.error(ResultEnum.USER_SAVE_FAIL);
		}
		return Result.success(results);
	}

	public Result<Integer> update(PlatformUpdateREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		Platform record = BeanUtil.copyProperties(req, Platform.class);
		int results = dao.updateById(record);
		if (results < 0) {
			return Result.error(ResultEnum.USER_UPDATE_FAIL);
		}
		return Result.success(results);
	}

	public Result<PlatformViewRESQ> view(PlatformViewREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		Platform record = dao.getById(req.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("找不到平台信息");
		}
		return Result.success(BeanUtil.copyProperties(record, PlatformViewRESQ.class));
	}

	public Result<Integer> delete(PlatformDeleteREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		int results = dao.deleteById(req.getId());
		if (results < 0) {
			return Result.error(ResultEnum.USER_DELETE_FAIL);
		}
		return Result.success(results);
	}

}
