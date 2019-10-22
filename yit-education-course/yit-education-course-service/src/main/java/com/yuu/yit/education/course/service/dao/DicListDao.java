package com.yuu.yit.education.course.service.dao;

import com.yuu.yit.education.course.service.dao.impl.mapper.entity.DicList;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.DicListExample;
import com.yuu.yit.education.util.base.Page;

public interface DicListDao {
    int save(DicList record);

    int deleteById(Long id);

    int updateById(DicList record);

    DicList getById(Long id);

    Page<DicList> listForPage(int pageCurrent, int pageSize, DicListExample example);
}