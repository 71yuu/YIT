package com.yuu.yit.education.user.service.api.auth;

import com.yuu.yit.education.user.service.biz.auth.AuthApiLecturerBiz;
import com.yuu.yit.education.user.service.common.bo.LecturerViewBO;
import com.yuu.yit.education.user.service.common.dto.LecturerViewDTO;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 讲师信息-审核
 *
 * @author Yuu
 */
@RestController
@RequestMapping(value = "/user/auth/lecturer")
public class AuthApiLecturerController extends BaseController {

	@Autowired
	private AuthApiLecturerBiz biz;

	/**
	 * 讲师信息查看接口
	 * 
	 * @param lecturerUserNo
	 * @author Yuu
	 */
	@ApiOperation(value = "讲师查看接口", notes = "根据讲师用户编号查看讲师信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<LecturerViewDTO> view(@RequestBody LecturerViewBO lecturerViewBO) {
		return biz.view(lecturerViewBO);
	}

	/**
	 * 讲师信息修改接口
	 */
	@ApiOperation(value = "讲师修改接口", notes = "修改讲师信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody LecturerViewBO lecturerViewBO) {
		return biz.update(lecturerViewBO);
	}

}
