package com.cn.ncvt.filter;


import com.cn.ncvt.biz.PermissionBiz;
import com.cn.ncvt.entity.User;
import com.cn.ncvt.util.SpringContextUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Set;

import static com.cn.ncvt.util.Token.verifyToken;

public class URLPathMatchingFilter extends PathMatchingFilter {

	@Autowired
    PermissionBiz permissionBiz;

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		if(null==permissionBiz)
            permissionBiz = SpringContextUtils.getContext().getBean(PermissionBiz.class);
        response.setContentType("application/json;charset=UTF-8");

        HttpServletRequest req = (HttpServletRequest) request;
        String headerToken = req.getHeader("token");

        String sessionId = req.getHeader("sessionId");
        //System.out.println("头部传递token:" + headerToken);
        System.out.println("头部传递sessionId:" + sessionId);
        System.out.println("当前sessionId:" + WebUtils.toHttp(request).getSession().getId());

        //获取请求连接
        String requestURI = getPathWithinApplication(request);
        System.out.println("requestURI:" + requestURI);

        //Subject subject = new Subject.Builder().sessionId(sessionId).buildSubject();
        Subject subject = SecurityUtils.getSubject();
        System.out.println("当前用户:" + subject.getPrincipal());
        User user = (User) subject.getPrincipal();

        if (headerToken == null || sessionId == null || user == null) {
            WebUtils.issueRedirect(request, response, "/unauthorized");
            return false;
        }
        System.out.println(user.getUsername());

        if (!subject.isAuthenticated()) {
            WebUtils.issueRedirect(request, response, "/unauthorized");
            return false;
        }

        if (!verifyToken(headerToken)) {
            WebUtils.issueRedirect(request, response, "/unauthorized");
            return false;
        }

        boolean needInterceptor = permissionBiz.needInterceptor(requestURI);
        if (!needInterceptor) {
            return true;
        } else {
            boolean hasPermission = false;
            List<String> permissionUrls = permissionBiz.listPermissionURLs(user.getId());
            for (String url : permissionUrls) {
                // 这就表示当前用户有这个权限
                if (url.equals(requestURI)) {
                    hasPermission = true;
                    break;
                }
            }
            if (hasPermission)
                return true;
            else {
                WebUtils.issueRedirect(request, response, "/unauthorization");
                return false;
            }
        }
    }
}