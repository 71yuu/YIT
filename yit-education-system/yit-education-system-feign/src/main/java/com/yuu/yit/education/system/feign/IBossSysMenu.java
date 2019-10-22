package com.yuu.yit.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.system.common.interfaces.BossSysMenu;

/**
 * 菜单信息 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-system-service")
public interface IBossSysMenu extends BossSysMenu {

}
