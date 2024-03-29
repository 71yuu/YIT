package com.yuu.yit.education.system.service.dao;

import com.yuu.yit.education.system.service.dao.impl.mapper.entity.MsgUser;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.MsgUserExample;
import com.yuu.yit.education.util.base.Page;

public interface MsgUserDao {
	int save(MsgUser record);

	int deleteById(Long id);

	int updateById(MsgUser record);

	MsgUser getById(Long id);

	Page<MsgUser> listForPage(int pageCurrent, int pageSize, MsgUserExample example);

	int deleteByMsgId(Long id);

	/**
	 * 获得学员未读消息总条数
	 * 
	 * @param userNo
	 * @param isRead
	 * @return
	 * @author Yuu
	 */
	int countByUserNoAndIsRead(Long userNo, Integer isRead);

}