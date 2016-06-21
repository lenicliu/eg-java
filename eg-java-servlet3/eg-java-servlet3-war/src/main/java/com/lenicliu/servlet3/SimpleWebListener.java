package com.lenicliu.servlet3;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class SimpleWebListener implements ServletContextListener, HttpSessionListener {
	private static Logger LOG = LoggerFactory.getLogger(SimpleWebListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOG.info("context initialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOG.info("context destroyed");
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		LOG.info("session created:" + se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		LOG.info("session destroyed" + se.getSession().getId());
	}
}