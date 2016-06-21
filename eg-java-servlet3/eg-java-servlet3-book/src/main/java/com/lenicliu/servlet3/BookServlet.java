package com.lenicliu.servlet3;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class BookServlet extends HttpServlet {

	private static final long serialVersionUID = -6117162048175739894L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> book1 = new HashMap<>();
		book1.put("id", "1");
		book1.put("name", "Spring in Action");

		Map<String, Object> book2 = new HashMap<>();
		book2.put("id", "2");
		book2.put("name", "Thinking in Java");

		resp.setContentType("application/json");
		resp.getWriter().write(JSON.toJSONString(Arrays.asList(book1, book2)));
		resp.getWriter().flush();
		resp.getWriter().close();
	}
}
