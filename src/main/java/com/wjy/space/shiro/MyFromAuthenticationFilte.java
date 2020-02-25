package com.wjy.space.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.wjy.space.pojo.User;

public class MyFromAuthenticationFilte extends FormAuthenticationFilter{
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest req=(HttpServletRequest) request;
		String verifyCode=request.getParameter("verifyCode");
		String randCode=(String) req.getSession().getAttribute("rand");
		System.out.println("rand");
		if(StringUtils.isNotBlank(verifyCode)&&StringUtils.isNotBlank(randCode)) {
			verifyCode=verifyCode.toLowerCase();
			randCode=randCode.toLowerCase();
			if(!verifyCode.equals(randCode)) {
				req.setAttribute("errorMsg", "验证码错误！");
				req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, response);
				return false;
			}
		}
		return super.onAccessDenied(request, response);
	}
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		WebUtils.getAndClearSavedRequest(request);
		return super.onLoginSuccess(token, subject, request, response);
	}
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Subject subject=getSubject(request, response);
		Session session = subject.getSession();
		if(!subject.isAuthenticated()&&subject.isRemembered()) {
			User user=(User) subject.getPrincipal();
			session.setAttribute("user", user);
		}
		return subject.isAuthenticated() || subject.isRemembered();
	}
}
