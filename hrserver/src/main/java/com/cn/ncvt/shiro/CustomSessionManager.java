package com.cn.ncvt.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @version : V1.0
 * @ClassName: CustomSessionManager
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/9/26 20:18
 **/
public class CustomSessionManager extends DefaultWebSessionManager {

    private static final String SESSIONID ="sessionId";

    private static final String REFERENCED_SESSION_ID_SOURCE = "cookie";

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // TODO Auto-generated method stub
        String sessionId = WebUtils.toHttp(request).getHeader(SESSIONID);
        if (StringUtils.isNotEmpty(sessionId)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        }
        return super.getSessionId(request, response);
    }

}
