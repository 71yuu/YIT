package com.yuu.yit.education.system.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.system.common.bean.qo.MsgTemplateQO;
import com.yuu.yit.education.system.common.bean.vo.MsgTemplateVO;
import com.yuu.yit.education.system.common.interfaces.BossMsgTemplate;
import com.yuu.yit.education.system.service.controller.biz.BossMsgTemplateBiz;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;


/**
 * 消息模板
 *
 * @author Yuu
 */
@RestController
public class BossMsgTemplateController extends BaseController implements BossMsgTemplate {

    @Autowired
    private BossMsgTemplateBiz biz;

    @Override
    public Page<MsgTemplateVO> listForPage(@RequestBody MsgTemplateQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody MsgTemplateQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody MsgTemplateQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public MsgTemplateVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

   /* @Override
    public String getRemarkByActTypeNoticeTypeAndOrgNo(@PathVariable Integer actType, @PathVariable Integer noticeType, @PathVariable String orgNo) {
        return biz.getRemarkByActTypeNoticeTypeAndOrgNo(actType, noticeType, orgNo);
    }

	@Override
	public MsgTemplateVO getByActTypeAndNoticeTypeAndOrgNo(@RequestBody MsgTemplateQO qo) {
		 return biz.getByActTypeAndNoticeTypeAndOrgNo(qo);
	}*/

}
