package com.yuu.yit.education.course.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.course.common.bean.qo.DicListQO;
import com.yuu.yit.education.course.common.bean.vo.DicListVO;
import com.yuu.yit.education.course.service.dao.DicListDao;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.DicList;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.DicListExample;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.tools.BeanUtil;

/**
 * 数据字典明细表
 *
 * @author Yuu
 */
@Component
public class BossDicListBiz {

	@Autowired
	private DicListDao dao;

	public Page<DicListVO> listForPage(DicListQO qo) {
		DicListExample example = new DicListExample();
		example.setOrderByClause(" id desc ");
		Page<DicList> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, DicListVO.class);
	}

	public int save(DicListQO qo) {
		DicList record = BeanUtil.copyProperties(qo, DicList.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public DicListVO getById(Long id) {
		DicList record = dao.getById(id);
		return BeanUtil.copyProperties(record, DicListVO.class);
	}

	public int updateById(DicListQO qo) {
		DicList record = BeanUtil.copyProperties(qo, DicList.class);
		return dao.updateById(record);
	}

}
