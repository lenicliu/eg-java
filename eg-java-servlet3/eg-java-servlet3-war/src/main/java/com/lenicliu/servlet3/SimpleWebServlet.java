package com.lenicliu.servlet3;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(//
		name = "WebServlet Example", //
		urlPatterns = { "*.do", "/web/*", "/webapi" }, //
		loadOnStartup = 1, //
		description = "This is servlet3 showcase servlet", //
		displayName = "WebServlet Example", //
		initParams = { //
				@WebInitParam(name = "param1", value = "100", description = "This is param 1"), //
				@WebInitParam(name = "param2", value = "config.xml", description = "This is param 2"), //
				@WebInitParam(name = "param3", value = "20160209202000", description = "This is param 3")//
		})
public class SimpleWebServlet extends HttpServlet {
	private static final String	UTF_8				= "UTF-8";
	private static final long	serialVersionUID	= -6087859606279926562L;
	private int					param1;
	private String				param2;
	private Date				param3;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getRequestURI().replaceAll(req.getContextPath(), "").equals("/webapi")) {
			resp.setCharacterEncoding(UTF_8);
			resp.setContentType("text/html");
			resp.getWriter().write("param1=" + param1 + "<br/>");
			resp.getWriter().write("param2=" + param2 + "<br/>");
			resp.getWriter().write("param3=" + param3 + "<br/>");
			resp.getWriter().write("Hi, this is SimpleWebServlet<br/>");
			resp.getWriter().close();
		} else {
			resp.setCharacterEncoding(UTF_8);
			resp.setContentType("text/html");
			resp.getWriter().write("ServletPath = " + req.getServletPath() + "<br/>");
			resp.getWriter().close();
		}
	}

	@Override
	public void init() throws ServletException {
		try {
			this.param1 = Integer.parseInt(getServletConfig().getInitParameter("param1"));
			this.param2 = getServletConfig().getInitParameter("param2");
			this.param3 = new SimpleDateFormat("yyyyMMddHHmmss").parse(getServletConfig().getInitParameter("param3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}