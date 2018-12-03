package com.mvc.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author by Dawei on 2018/9/28.
 * 时间转化工具
 */
public class DateHelper {


    private static final Logger logger = LoggerFactory.getLogger(DateHelper.class);

    /**
     *  时间转化
     * @param pattern 格式
     * @param time Date 时间
     */
    public static String turnDateByPattern(Date time, String pattern) {
        if(time != null && pattern != null && !"".equals(pattern)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(time);
        }
        return null;
    }

    /**
     * 时间格式的转化
     * @param dateStr 时间字符串
     * @param pattern 时间格式
     * @return 返回时间Date
     */
    public static Date getDateByPattern(String dateStr, String pattern) {
        if(dateStr != null && !"".equals(dateStr) && pattern != null && !"".equals(pattern)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            try {
                return simpleDateFormat.parse(dateStr);
            } catch (ParseException e) {
                logger.info("Str to Date failed, dateStr={}, e=", dateStr, e);
            }
        }
        return null;
    }


}
