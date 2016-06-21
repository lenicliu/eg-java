package com.lenicliu.servlet3;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(//
		urlPatterns = { "/*" }, //
		filterName = "SimpleWebFilter", //
		displayName = "SimpleWebFilter", //
		initParams = { //
				@WebInitParam(name = "p1", value = "v1"), //
				@WebInitParam(name = "p2", value = "v2")//
		})
public class SimpleWebFilter implements Filter {

	private static Logger LOG = LoggerFactory.getLogger(SimpleWebFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.info("init filter");
		LOG.info("p1=" + filterConfig.getInitParameter("p1"));
		LOG.info("p2=" + filterConfig.getInitParameter("p2"));
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOG.info("before filter");
		chain.doFilter(request, response);
		LOG.info("after filter");
	}

	@Override
	public void destroy() {
		LOG.info("destroy filter");
	}
}