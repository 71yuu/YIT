package com.yuu.yit.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.system.service.biz.ApiSysLogBiz;
import com.yuu.yit.education.util.base.BaseController;

/**
 * 后台操作日志表 
 *
 * @author Yuu
 */
@RestController
public class ApiSysLogController extends BaseController {

    @Autowired
    private ApiSysLogBiz biz;
    
}
