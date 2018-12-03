package com.mvc.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author by Dawei on 2018/8/22. Request参数处理
 */
public class RequestHelper {

    private static final Logger logger = LoggerFactory.getLogger(RequestHelper.class);

    /* ###################################### Attribute 与 Reuqest ################################# */

    /* Request 域中的获取的基本方法 */
    private static String getStringParm(HttpServletRequest request, String parmName) {
        if (request != null && !"".equals(parmName)) {
            return request.getParameter(parmName);
        }
        return null;
    }

    public static String getStringParm(HttpServletRequest request, String parmName,
                                       String defaultValue) {
        return getStringParm(request, parmName) == null ? defaultValue
                : getStringParm(request, parmName);

    }

    public static Integer getIntParm(HttpServletRequest request, String parmName, Integer defaultValue) {
        Integer result = defaultValue;
        String stringParm = getStringParm(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Integer.parseInt(stringParm);
            } catch (Exception e) {
                logger.warn("getIntParm 类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }

    public static Double getDoubleParm(HttpServletRequest request, String parmName, Double defaultValue) {
        Double result = defaultValue;
        String stringParm = getStringParm(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Double.parseDouble(stringParm);
            } catch (Exception e) {
                logger.warn("getDoubleParm 类型转化异常， parmName={}， e=", parmName, e);
            }

        }
        return result;
    }

    public static Long getLongParm(HttpServletRequest request, String parmName, Long defaultValue) {
        Long result = defaultValue;
        String stringParm = getStringParm(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Long.parseLong(stringParm);
            } catch (Exception e) {
                logger.warn("getLongParm 类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }

    /* ###################################### Header ################################# */

    /* Header 域中的获取的基本方法 */
    private static String getHeaderStringPara(HttpServletRequest request, String headerName) {
        String result = null;
        if (request != null && !"".equals(headerName)) {
            result = request.getHeader(headerName);
        }
        return result;
    }

    public static String getHeaderStringParm(HttpServletRequest request, String parmName,
                                             String defaultValue) {
        return getHeaderStringPara(request, parmName) == null ? defaultValue
                : getHeaderStringPara(request, parmName);

    }

    public static Integer getHeaderIntParm(HttpServletRequest request, String parmName,
                                           Integer defaultValue) {
        Integer result = defaultValue;
        String stringParm = getHeaderStringPara(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Integer.parseInt(stringParm);
            } catch (Exception e) {
                logger.warn("类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }

    public static Double getHeaderDoubleParm(HttpServletRequest request, String parmName,
                                             Double defaultValue) {
        Double result = defaultValue;
        String stringParm = getHeaderStringPara(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Double.parseDouble(stringParm);
            } catch (Exception e) {
                logger.warn("getHeaderDoubleParm 类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }

    public static Long getHeaderLongParm(HttpServletRequest request, String parmName, Long defaultValue) {
        Long result = defaultValue;
        String stringParm = getHeaderStringPara(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Long.parseLong(stringParm);
            } catch (Exception e) {
                logger.warn("getHeaderLongParm 类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }


    /* ###################################### Cookie ################################# */

    /* Cookie 域中的获取的基本方法 */
    private static String getCookieStringPara(HttpServletRequest request, String cookieName) {
        if (request != null && !"".equals(cookieName)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equalsIgnoreCase(cookieName)) {
                        return cookie.getValue();
                    }
                }
            }
        }
        return null;
    }

    public static String getCookieStringParm(HttpServletRequest request, String parmName,
                                             String defaultValue) {
        return getCookieStringPara(request, parmName) == null ? defaultValue
                : getCookieStringPara(request, parmName);

    }

    public static Integer getCookieIntParm(HttpServletRequest request, String parmName,
                                           Integer defaultValue) {
        Integer result = defaultValue;
        String stringParm = getCookieStringPara(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Integer.parseInt(stringParm);
            } catch (Exception e) {
                logger.warn("getCookieIntParm 类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }

    public static Double getCookieDoubleParm(HttpServletRequest request, String parmName,
                                             Double defaultValue) {
        Double result = defaultValue;
        String stringParm = getCookieStringPara(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Double.parseDouble(stringParm);
            } catch (Exception e) {
                logger.warn("getCookieDoubleParm 类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }

    public static Long getCookieLongParm(HttpServletRequest request, String parmName, Long defaultValue) {
        Long result = defaultValue;
        String stringParm = getCookieStringPara(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Long.parseLong(stringParm);
            } catch (Exception e) {
                logger.warn("getCookieLongParm 类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }


    //###################################获取用户信息#############################################

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
