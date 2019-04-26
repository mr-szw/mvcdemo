package com.mvc.demo.interceptors;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Dawei 2019/3/14
 */
public class UserLoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(UserLoginInterceptor.class);

    /* 前置拦截 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();
        logger.info("Shiro login check ---- for requestURI={}", requestURI);
        Subject subject = SecurityUtils.getSubject();
        //已经认证跳转取去主页面 否则去登陆
        if (subject.isAuthenticated()) {
            return true;
        } else {
            try {
                response.sendRedirect("/toLogin");
            } catch (IOException e) {
                logger.error("Can`t redirect to login page.");
            }
        }
        return false;
    }

    //方法业务处理之后再调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    //页面渲染完成之后 通常用于清除某些资源，类似Finally
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
