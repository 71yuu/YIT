package com.yuu.yit.education.course.service.dao;

import com.yuu.yit.education.course.service.dao.impl.mapper.entity.FileStorage;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.FileStorageExample;
import com.yuu.yit.education.util.base.Page;

public interface FileStorageDao {
    int save(FileStorage record);

    int deleteById(Long id);

    int updateById(FileStorage record);

    FileStorage getById(Long id);

    Page<FileStorage> listForPage(int pageCurrent, int pageSize, FileStorageExample example);
}