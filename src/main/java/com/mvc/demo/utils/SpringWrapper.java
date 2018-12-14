package com.mvc.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * @author Dawei on 2018/3/28
 */
public class SpringWrapper implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(SpringWrapper.class);
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(Class<T> type) throws BeansException {
        logger.info("【SpringWrapper】 ： getBean by Class ,context is {} "+ context);
        return context.getBean(type);
    }

    public static Object getBean(String name) throws BeansException {
        logger.info("【SpringWrapper】 ： getBean by name ,context is {}"+ context);
        return context.getBean(name);
    }
}
