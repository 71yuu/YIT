package com.yuu.yit.education.user.service.dao;

import com.yuu.yit.education.user.service.dao.impl.mapper.entity.SendSmsLog;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.SendSmsLogExample;
import com.yuu.yit.education.util.base.Page;

public interface SendSmsLogDao {
    int save(SendSmsLog record);

    int deleteById(Long id);

    int updateById(SendSmsLog record);

    SendSmsLog getById(Long id);

    Page<SendSmsLog> listForPage(int pageCurrent, int pageSize, SendSmsLogExample example);
}