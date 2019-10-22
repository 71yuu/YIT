package com.yuu.yit.education.system.service.biz;

import com.yuu.yit.education.system.service.common.dto.WebsiteDTO;
import com.yuu.yit.education.system.service.dao.WebsiteDao;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.Website;
import com.yuu.yit.education.util.base.Result;
import com.yuu.yit.education.util.enums.StatusIdEnum;
import com.yuu.yit.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 站点信息
 *
 * @author Yuu
 */
@Component
public class ApiWebsiteBiz {

	@Autowired
	private WebsiteDao websitedao;

	public Result<WebsiteDTO> get() {
		Website website = websitedao.getByStatusId(StatusIdEnum.YES.getCode());
		WebsiteDTO dto = BeanUtil.copyProperties(website, WebsiteDTO.class);
		return Result.success(dto);
	}

}
