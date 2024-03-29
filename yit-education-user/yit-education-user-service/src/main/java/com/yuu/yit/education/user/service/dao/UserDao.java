package com.yuu.yit.education.user.service.dao;

import com.yuu.yit.education.user.common.bean.vo.UserExtMsgVO;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.User;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.UserExample;
import com.yuu.yit.education.util.base.Page;

public interface UserDao {
	int save(User record);

	int deleteById(Long id);

	int updateById(User record);

	User getById(Long id);

	Page<User> listForPage(int pageCurrent, int pageSize, UserExample example);

	User getByMobile(String mobile);

	User getByUserNo(Long userNo);

	/**
	 * 分页缓存用户信息，站内信发送用
	 * 
	 * @param statusId
	 * @param pageCurrent
	 * @param pageSize
	 * @return
	 * @author Yuu
	 */
	Page<UserExtMsgVO> pageByStatusIdForMsg(Integer statusId, int pageCurrent, int pageSize);
}