package com.yuu.yit.education.system.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.system.common.bean.qo.MsgTemplateQO;
import com.yuu.yit.education.system.common.bean.vo.MsgTemplateVO;
import com.yuu.yit.education.system.service.dao.MsgTemplateDao;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.MsgTemplate;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.MsgTemplateExample;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.tools.BeanUtil;

/**
 * 消息模板 
 *
 * @author Yuu
 */
@Component
public class BossMsgTemplateBiz {

	@Autowired
	private MsgTemplateDao dao;

	public Page<MsgTemplateVO> listForPage(MsgTemplateQO qo) {
	    MsgTemplateExample example = new MsgTemplateExample();
	    example.setOrderByClause(" status_id desc, sort desc, id desc ");
        Page<MsgTemplate> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, MsgTemplateVO.class);
	}

	public int save(MsgTemplateQO qo) {
        MsgTemplate record = BeanUtil.copyProperties(qo, MsgTemplate.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public MsgTemplateVO getById(Long id) {
	    MsgTemplate record = dao.getById(id);
		return BeanUtil.copyProperties(record, MsgTemplateVO.class);
	}

	public int updateById(MsgTemplateQO qo) {
	    MsgTemplate record = BeanUtil.copyProperties(qo, MsgTemplate.class);
		return dao.updateById(record);
	}
	
}
