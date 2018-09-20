
package com.pengzu.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {


    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     * 得到请求的IP地址
     *
     * @param request
     * @return
     */
    public static String getIp1(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isBlank(ip)) {
            ip = request.getHeader("Host");
        }
        if (StringUtils.isBlank(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StringUtils.isBlank(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     * 得到请求的根目录
     *
     * @param request
     * @return
     */
    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        if (request.getServerPort() != 80) {
            String basePath = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + path;
            return basePath;
        }
        String basePath = request.getScheme() + "://" + request.getServerName()
                + path;
        return basePath;
    }

    public static String getBasePathNoPort(HttpServletRequest request) {
        String path = request.getContextPath();
        if (request.getServerPort() != 80 && request.getServerPort() != 443) {
            String basePath = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + path;
            return basePath;
        }
        String basePath = request.getScheme() + "://" + request.getServerName()
                + path;
        return basePath;
    }

    /**
     * 得到结构目录
     *
     * @param request
     * @return
     */
    public static String getContextPath(HttpServletRequest request) {
        String path = request.getContextPath();
        return path;
    }
}
