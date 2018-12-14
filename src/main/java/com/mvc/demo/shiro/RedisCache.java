package com.mvc.demo.shiro;

import com.mvc.demo.utils.RedisHelper;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Dawei 2018/12/14
 */
@Component
public class RedisCache<K, V> implements Cache<K, V> {

    private static final String REDIS_PRE = "PRE:";
    private static final Integer GENERAL_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7 * 2;

    @Override
    public V get(K k) throws CacheException {
        String strValue = null;
        if (k != null) {
            if (k instanceof String) {
                strValue = RedisHelper.get(REDIS_PRE + k.toString());
            }
            if (strValue != null) {
                return (V) SerializationUtils.deserialize(strValue.getBytes());
            }
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        byte[] serialize = SerializationUtils.serialize(v);
        if (k != null && serialize != null) {
            RedisHelper.set(REDIS_PRE + k.toString(), new String(serialize) , GENERAL_EXPIRE_TIME);
        }
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        V value = null;
        if (k != null) {
            if (k instanceof String) {
                String strValue = RedisHelper.get(REDIS_PRE + k.toString());
                if (strValue != null) {
                    value = (V) SerializationUtils.deserialize(strValue.getBytes());
                    RedisHelper.del(REDIS_PRE + k.toString());
                }
            }
        }
        return value;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        Set<String> keys = RedisHelper.getKeys(REDIS_PRE);
        if (keys != null) {
            return keys.size();
        }
        return 0;
    }

    @Override
    public Set<K> keys() {
        Set<String> keys = RedisHelper.getKeys(REDIS_PRE);
        if (keys != null) {
            return keys.stream().map(key -> (K) key).collect(Collectors.toSet());
        }
        return null;
    }

    @Override
    public Collection<V> values() {
        Set<String> keys = RedisHelper.getKeys(REDIS_PRE);

        Set<V> resultSet = new HashSet<>();
        if (keys != null) {
            keys.forEach(key -> {
                String value = RedisHelper.get(REDIS_PRE + key);
                if (value != null) {
                    resultSet.add((V) SerializationUtils.deserialize(value.getBytes()));
                }
            });
        }
        return resultSet;
    }
}
