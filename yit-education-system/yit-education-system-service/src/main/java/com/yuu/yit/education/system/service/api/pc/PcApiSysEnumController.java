package com.yuu.yit.education.system.service.api.pc;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.system.service.common.bo.SysEnumBO;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Result;
import com.yuu.yit.education.util.tools.EnumUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 获取枚举信息
 * @author liaoh
 *
 */
@RestController
@RequestMapping(value = "/system/pc/api/sys/enum")
public class PcApiSysEnumController extends BaseController {

	@ApiOperation(value = "获取枚举信息", notes = "获取枚举信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<ArrayList> getEnumInfo(@RequestBody SysEnumBO sysEnumBO) {
       if (sysEnumBO.getEnumName() == null) {
            return Result.error("枚举[" + sysEnumBO.getEnumName() + "]不存在");
        }
		String className = "com.yuu.yit.education.util.enums."+sysEnumBO.getEnumName();
        try {
        	Class clazz = Class.forName(className);
        	if(clazz == null) {
        		return Result.error("找不到该枚举信息");
        	}
            return Result.success(new ArrayList<>(EnumUtil.toList(clazz)));
        } catch (ClassNotFoundException e) {
            return Result.error("获取枚举失败");
        }
    }

}
