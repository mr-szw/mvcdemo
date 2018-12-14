package com.mvc.demo.service;

import com.mvc.demo.utils.RedisHelper;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dawei 2018/12/14
 */
@Service
public class RedisSessionDao extends AbstractSessionDAO {

    private Integer generalExpireTime;

    public Integer getGeneralExpireTime() {
        return generalExpireTime;
    }

    public void setGeneralExpireTime(Integer generalExpireTime) {
        this.generalExpireTime = generalExpireTime;
    }

    private static final String NULL_MESSAGE = "数据为空";

    private static final String REDIS_PRE = "PRE:";

    /* 两周 */
    private static final Integer GENERAL_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7 * 2;


    @Override
    protected Serializable doCreate(Session session) {
        Assert.notNull(session, NULL_MESSAGE);
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        String keyWithPre = REDIS_PRE + session.getId().toString();
        byte[] sessionValueByte = SerializationUtils.serialize(session);
        if (sessionValueByte != null) {
            String sessionValue = new String(sessionValueByte);
            if(generalExpireTime == null) {
                generalExpireTime = GENERAL_EXPIRE_TIME;
            }
            boolean set = RedisHelper.set(keyWithPre, sessionValue, generalExpireTime);
        }
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        //Assert.notNull(serializable, NULL_MESSAGE);
        Session session = null;
        if (serializable != null) {
            String value = RedisHelper.get(REDIS_PRE + serializable.toString());
            if (!StringUtils.isEmpty(value)) {
                session = (Session) SerializationUtils.deserialize(value.getBytes());
            }
        }
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (session != null && session.getId() != null) {
            String keyWithPre = REDIS_PRE + session.getId().toString();
            byte[] sessionValueByte = SerializationUtils.serialize(session);
            String sessionValue = new String(sessionValueByte);
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
                    Session session = (Session) SerializationUtils.deserialize(value.getBytes());
                    sessionValueSet.add(session);
                }
            });
        }
        return sessionValueSet;
    }
}
