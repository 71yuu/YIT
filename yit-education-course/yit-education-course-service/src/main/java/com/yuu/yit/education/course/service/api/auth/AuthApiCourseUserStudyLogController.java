package com.yuu.yit.education.course.service.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.course.service.biz.auth.AuthApiCourseUserStudyLogBiz;
import com.yuu.yit.education.course.service.common.bo.auth.AuthCourseUserStudyLogPageBO;
import com.yuu.yit.education.course.service.common.dto.auth.AuthCourseUserStudyLogPageDTO;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息-审核
 *
 * @author Yuu
 */
@RestController
@RequestMapping(value = "/course/auth/course/user/study/log")
public class AuthApiCourseUserStudyLogController extends BaseController {

	@Autowired
	private AuthApiCourseUserStudyLogBiz biz;

	/**
	 * 最近学习日志分页列出接口
	 * 
	 * @param authCourseUserStudyLogPageBO
	 * @return
	 * @author Yuu
	 */
	@ApiOperation(value = "分页列出接口", notes = "最近学习分页列出接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<AuthCourseUserStudyLogPageDTO>> list(@RequestBody AuthCourseUserStudyLogPageBO authCourseUserStudyLogPageBO) {
		return biz.list(authCourseUserStudyLogPageBO);
	}

}
