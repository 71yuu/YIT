package com.yuu.yit.education.course.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.course.common.bean.qo.CourseCategoryQO;
import com.yuu.yit.education.course.common.bean.vo.CourseCategoryVO;
import com.yuu.yit.education.course.common.interfaces.BossCourseCategory;
import com.yuu.yit.education.course.service.controller.biz.BossCourseCategoryBiz;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;

/**
 * 课程分类
 *
 * @author Yuu
 */
@RestController
public class BossCourseCategoryController extends BaseController implements BossCourseCategory {

	@Autowired
	private BossCourseCategoryBiz biz;

	@Override
	public Page<CourseCategoryVO> listForPage(@RequestBody CourseCategoryQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody CourseCategoryQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@PathVariable(value = "id") Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody CourseCategoryQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public CourseCategoryVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

	@Override
	public List<CourseCategoryVO> listByFloor(@PathVariable(value = "floor") Integer floor) {
		return biz.listByFloor(floor);
	}

	@Override
	public List<CourseCategoryVO> listByParentId(@PathVariable(value = "parentId") Long parentId) {
		return biz.listByParentId(parentId);
	}

	@Override
	public List<CourseCategoryVO> listByFloorAndCategoryId(@RequestBody CourseCategoryQO qo) {
		return biz.listByFloorAndCategoryId(qo);
	}
}
