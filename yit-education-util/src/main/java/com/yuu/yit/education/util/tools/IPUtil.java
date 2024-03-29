package com.yuu.yit.education.util.tools;

import javax.servlet.http.HttpServletRequest;

/**
 * IP 地址获取工具类
 *
 * @author by Yuu
 */
public class IPUtil {
    /**
     * 获取IP
     * @param request
     * @return
     */
    public static String getIP(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        System.out.println("ip:"+ip);
        String headerIP = request.getHeader("x-real-ip");
        if(headerIP == null || "".equals(headerIP) || "null".equals(headerIP)){
            headerIP = request.getHeader("x-forwarded-for");
        }
        System.out.println("headerIP:"+headerIP);
        if(headerIP !=null && !"".equals(headerIP) && !"null".equals(headerIP)){
            ip = headerIP;
        }
        return ip;
    }
}