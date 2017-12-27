package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;

public class LoginFilter implements  Filter{
	private FilterConfig config;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	@Override
	public void destroy() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
/*沒登入重導至首頁*/ 
		HttpSession session = req.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member"); 
		if( memberVO == null ){
			res.sendRedirect(req.getContextPath()+"/front_end/index.jsp");
			return;
		}else{
			chain.doFilter(request, response);
		}
	}
}
