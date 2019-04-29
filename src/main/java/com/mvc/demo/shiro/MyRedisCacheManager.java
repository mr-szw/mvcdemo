package com.mvc.demo.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dawei 2018/12/14
 */
public class MyRedisCacheManager implements CacheManager {

    private static final Logger logger = LoggerFactory.getLogger(MyRedisCacheManager.class);

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        logger.debug("Init redis cache manager ------");
        return new RedisCache<>();
    }
}
