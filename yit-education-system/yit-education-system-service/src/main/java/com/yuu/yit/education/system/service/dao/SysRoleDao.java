package com.yuu.yit.education.system.service.dao;

import com.yuu.yit.education.system.service.dao.impl.mapper.entity.SysRole;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.SysRoleExample;
import com.yuu.yit.education.util.base.Page;

public interface SysRoleDao {
    int save(SysRole record);

    int deleteById(Long id);

    int updateById(SysRole record);

    int updateByExampleSelective(SysRole record, SysRoleExample example);

    SysRole getById(Long id);

    Page<SysRole> listForPage(int pageCurrent, int pageSize, SysRoleExample example);
}