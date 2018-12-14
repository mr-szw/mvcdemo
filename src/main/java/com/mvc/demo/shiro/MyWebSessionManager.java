package com.mvc.demo.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * @author Dawei 2018/12/14
 * 自定义的Session Manager
 */
public class MyWebSessionManager extends DefaultWebSessionManager {


    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        ServletRequest request = null;

        if(sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        if(request != null &&sessionId != null) {
            Session session = (Session)request.getAttribute(sessionId.toString());
            if(session != null) {
                return session;
            }
        }
        Session session = super.retrieveSession(sessionKey);
        if(request != null && session != null) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
}
