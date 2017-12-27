package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class Pagefrom implements Filter {
	private FilterConfig config;
    
	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
/*取得當前頁面,重導和轉送時使用*/
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		session.setAttribute("from_forward", req.getServletPath());
		if(req.getQueryString() != null){
			session.setAttribute("from_redirect", req.getRequestURI()+"?"+req.getQueryString());
		}else{
			session.setAttribute("from_redirect", req.getRequestURI());
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}
}
