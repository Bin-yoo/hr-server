package com.cn.ncvt.filter;


import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import com.cn.ncvt.util.SpringContextUtils;
import com.cn.ncvt.util.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Set;

import static com.cn.ncvt.util.Token.verifyToken;

public class URLPathMatchingFilter extends PathMatchingFilter {
	/*@Autowired
	PermissionService permissionService;*/

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		/*if(null==permissionService)
			permissionService = SpringContextUtils.getContext().getBean(PermissionService.class);*/
        response.setContentType("application/json;charset=UTF-8");

        HttpServletRequest req = (HttpServletRequest)request;
        String headerToken = req.getHeader("token");

        //String sessionId = req.getHeader("sessionId");
        //System.out.println("头部传递sessionId:" + sessionId);
        //System.out.println("当前sessionId:" + WebUtils.toHttp(request).getSession().getId());
        //
        //获取请求连接
        String requestURI = getPathWithinApplication(request);
		System.out.println("requestURI:" + requestURI);

        //Subject subject = new Subject.Builder().sessionId(sessionId).buildSubject();
        Subject subject = SecurityUtils.getSubject();
        //System.out.println("当前用户:" + subject.getPrincipal());

        //System.out.println("isAuthenticated:"+subject.isAuthenticated());

        if (!subject.isAuthenticated()){
            WebUtils.issueRedirect(request, response, "/unauthorized");
            return false;
        } else {
            if (verifyToken(headerToken))
                return true;
            else
                return false;
        }




        /*if(requestURI.equals("/logout")){
            System.out.println("subject.isAuthenticated():"+subject.isAuthenticated());
            if (subject.isAuthenticated()){
                subject.logout();
            }
            *//*Result result = ResultFactory.buildSuccessResult("退出成功");
            PrintWriter out = response.getWriter();
            ObjectMapper om = new ObjectMapper();
            out.write(om.writeValueAsString(result));
            out.flush();
            out.close();*//*
            return true;
        } else {
            return false;
        }*/

		/*// 如果没有登录，就跳转到登录页面
		if (!subject.isAuthenticated()) {
			WebUtils.issueRedirect(request, response, "/login");
			return false;
		}

		// 看看这个路径权限里有没有维护，如果没有维护，一律放行(也可以改为一律不放行)
		System.out.println("permissionService:"+permissionService);
		boolean needInterceptor = permissionService.needInterceptor(requestURI);
		if (!needInterceptor) {
			return true;
		} else {
			boolean hasPermission = false;
			String userName = subject.getPrincipal().toString();
			Set<String> permissionUrls = permissionService.listPermissionURLs(userName);
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
				UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径 " + requestURI + " 的权限");

				subject.getSession().setAttribute("ex", ex);

				WebUtils.issueRedirect(request, response, "/unauthorized");
				return false;
			}

		}*/
	}
}