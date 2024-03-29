package com.yuu.yit.education.user.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yuu.yit.education.user.common.bean.vo.UserExtMsgVO;
import com.yuu.yit.education.user.service.common.AbstractBaseJdbc;
import com.yuu.yit.education.user.service.dao.UserDao;
import com.yuu.yit.education.user.service.dao.impl.mapper.UserMapper;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.User;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.UserExample;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.tools.IdWorker;

@Repository
public class UserDaoImpl extends AbstractBaseJdbc implements UserDao {
	@Autowired
	private UserMapper userMapper;

	@Override
	public int save(User record) {
		record.setId(IdWorker.getId());
		return this.userMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(User record) {
		return this.userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public User getById(Long id) {
		return this.userMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<User> listForPage(int pageCurrent, int pageSize, UserExample example) {
		int count = this.userMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<User>(count, totalPage, pageCurrent, pageSize, this.userMapper.selectByExample(example));
	}

	@Override
	public User getByMobile(String mobile) {
		UserExample example = new UserExample();
		example.createCriteria().andMobileEqualTo(mobile);
		List<User> list = this.userMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public User getByUserNo(Long userNo) {
		UserExample example = new UserExample();
		example.createCriteria().andUserNoEqualTo(userNo);
		List<User> list = this.userMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public Page<UserExtMsgVO> pageByStatusIdForMsg(Integer statusId, int pageCurrent, int pageSize) {
		StringBuffer sql = new StringBuffer("select user_no as userNo ,mobile from user_ext where 1=1");
		if (statusId != null) {
			sql.append(" and status_id =").append(statusId);
		}
		return queryForPage(sql.toString(), pageCurrent, pageSize, UserExtMsgVO.class);
	}
}