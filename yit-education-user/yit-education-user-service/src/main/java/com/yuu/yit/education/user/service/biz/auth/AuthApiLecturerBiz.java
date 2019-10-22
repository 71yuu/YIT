package com.yuu.yit.education.user.service.biz.auth;

import com.xiaoleilu.hutool.util.ObjectUtil;
import com.yuu.yit.education.user.service.common.bo.LecturerViewBO;
import com.yuu.yit.education.user.service.common.dto.LecturerViewDTO;
import com.yuu.yit.education.user.service.dao.LecturerDao;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.Lecturer;
import com.yuu.yit.education.util.base.Result;
import com.yuu.yit.education.util.enums.ResultEnum;
import com.yuu.yit.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 讲师信息 
 *
 * @author Yuu
 */
@Component
public class AuthApiLecturerBiz {

	@Autowired
	private LecturerDao lecturerDao;

	/**
	 * 讲师信息查看接口
	 * 
	 * @param lecturerViewBO
	 * @author Yuu
	 */
	public Result<LecturerViewDTO> view(LecturerViewBO lecturerViewBO) {
		if (null == lecturerViewBO.getLecturerUserNo()) {
			return Result.error("讲师编号不能为空");
		}
		Lecturer lecturer = lecturerDao.getByLecturerUserNo(lecturerViewBO.getLecturerUserNo());
		if (ObjectUtil.isNull(lecturer)) {
			return Result.error("找不到该讲师");
		}
		return Result.success(BeanUtil.copyProperties(lecturer, LecturerViewDTO.class));
	}

	/**
	 * 讲师信息修改接口
	 *
	 * @param lecturerViewBO
	 * @return
	 */
	public Result<Integer> update(LecturerViewBO lecturerViewBO) {
		if (null == lecturerViewBO.getLecturerUserNo()) {
			return Result.error("讲师编号不能为空");
		}
		Lecturer record = BeanUtil.copyProperties(lecturerViewBO, Lecturer.class);
		int resultNum = lecturerDao.updateById(record);
		if (resultNum > 0) {
			return Result.success(resultNum);
		}
		return Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());
	}
}
