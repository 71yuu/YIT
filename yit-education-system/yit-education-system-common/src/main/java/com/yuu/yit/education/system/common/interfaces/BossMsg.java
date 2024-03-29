package com.yuu.yit.education.system.common.interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuu.yit.education.system.common.bean.qo.MsgQO;
import com.yuu.yit.education.system.common.bean.vo.MsgVO;
import com.yuu.yit.education.util.base.Page;

/**
 * 站内信息表
 *
 * @author Yuu
 */
public interface BossMsg {

	@RequestMapping(value = "/boss/system/msg/listForPage")
	Page<MsgVO> listForPage(@RequestBody MsgQO qo);

	@RequestMapping(value = "/boss/system/msg/save")
	int save(@RequestBody MsgQO qo);

	@RequestMapping(value = "/boss/system/msg/deleteById")
	int deleteById(@RequestBody Long id);

	@RequestMapping(value = "/boss/system/msg/updateById")
	int updateById(@RequestBody MsgQO qo);

	@RequestMapping(value = "/boss/system/msg/getById")
	MsgVO getById(@RequestBody Long id);

	/**
	 * 手动推送站内信
	 * 
	 * @return
	 * @author Yuu
	 */
	@RequestMapping(value = "/boss/system/msg/pushByManual")
	int pushByManual(@RequestBody Long id);

	/**
	 * 定时器推送站内信
	 * 
	 * @param qo
	 * @return
	 * @author Yuu
	 */
	@RequestMapping(value = "/boss/system/msg/push")
	int push();

}
