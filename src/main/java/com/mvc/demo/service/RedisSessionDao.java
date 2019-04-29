package com.mvc.demo.service;

import com.mvc.demo.utils.RedisHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author Dawei 2018/12/14
 */
@Service
public class RedisSessionDao extends AbstractSessionDAO {

    private static final Logger logger = LoggerFactory.getLogger(RedisSessionDao.class);

    private Integer generalExpireTime;

    public Integer getGeneralExpireTime() {
        return generalExpireTime;
    }

    public void setGeneralExpireTime(Integer generalExpireTime) {
        this.generalExpireTime = generalExpireTime;
    }

    private static final String NULL_MESSAGE = "数据为空";

    private static final String REDIS_PRE = "SHIRO:SESSION:PRE:";

    /* 两周 */
    private static final Integer GENERAL_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7 * 2;


    @Override
    protected Serializable doCreate(Session session) {
        Assert.notNull(session, NULL_MESSAGE);
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        String keyWithPre = REDIS_PRE + session.getId().toString();
        String sessionValue = objectSerializable(session);
        if (sessionValue != null) {
            if (generalExpireTime == null) {
                generalExpireTime = GENERAL_EXPIRE_TIME;
            }
            boolean set = RedisHelper.set(keyWithPre, sessionValue, generalExpireTime);
        }
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        Assert.notNull(serializable, NULL_MESSAGE);
        Session session = null;
        String value = RedisHelper.get(REDIS_PRE + serializable.toString());
        if (!StringUtils.isEmpty(value)) {
            Object deserialize = objectDeserialization(value);
            session = (Session) deserialize;
        }
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session != null && session.getId() != null) {
            String keyWithPre = REDIS_PRE + session.getId().toString();
            String sessionValue = objectSerializable(session);
            boolean setReusult = RedisHelper.set(keyWithPre, sessionValue, GENERAL_EXPIRE_TIME);
        }
    }

    @Override
    public void delete(Session session) {
        if (session != null && session.getId() != null) {
            boolean del = RedisHelper.del(REDIS_PRE + session.getId().toString());
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessionValueSet = new HashSet<>();
        Set<String> sessionKey = RedisHelper.getKeys(REDIS_PRE);
        if (!CollectionUtils.isEmpty(sessionKey)) {
            sessionKey.forEach(sessionId -> {
                String value = RedisHelper.get(REDIS_PRE + sessionId);
                if (!StringUtils.isEmpty(value)) {
                    Session session = (Session) objectDeserialization(value);
                    sessionValueSet.add(session);
                }
            });
        }
        return sessionValueSet;
    }


    //对象序列化为字符串
    private static String objectSerializable(Object obj) {
        String serStr = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            serStr = byteArrayOutputStream.toString("ISO-8859-1");
            serStr = java.net.URLEncoder.encode(serStr, "UTF-8");

            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            logger.error("【Object Serialiable failed!】， e=", e);
        }

        return serStr;
    }

    //字符串反序列化为对象
    private static Object objectDeserialization(String serStr) {
        Object newObj = null;
        try {
            String redStr = java.net.URLDecoder.decode(serStr, "UTF-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            newObj = objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
        } catch (Exception e) {
            logger.error("【Object Deserialization failed!】， e=", e);
        }
        return newObj;
    }
}
