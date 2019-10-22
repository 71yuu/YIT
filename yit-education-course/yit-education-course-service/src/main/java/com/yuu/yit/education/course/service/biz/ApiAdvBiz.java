package com.yuu.yit.education.course.service.biz;

import com.xiaoleilu.hutool.util.CollectionUtil;
import com.yuu.yit.education.course.service.common.bo.AdvBO;
import com.yuu.yit.education.course.service.common.dto.AdvDTO;
import com.yuu.yit.education.course.service.common.dto.AdvListDTO;
import com.yuu.yit.education.course.service.dao.AdvDao;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.Adv;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.base.Result;
import com.yuu.yit.education.util.enums.StatusIdEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 广告信息
 *
 * @author Yuu
 */
@Component
public class ApiAdvBiz {

	@Autowired
	private AdvDao advDao;

	public Result<AdvListDTO> list(AdvBO advBO) {
		AdvListDTO dto = new AdvListDTO();
		System.out.println("sss");
		// 开始时间和结束时间
		List<Adv> advList = advDao.listByPlatShowAndStatusId(advBO.getPlatShow(), StatusIdEnum.YES.getCode());
		if (CollectionUtil.isNotEmpty(advList)) {
			dto.setAdvList(PageUtil.copyList(advList, AdvDTO.class));
		}
		return Result.success(dto);
	}

}
