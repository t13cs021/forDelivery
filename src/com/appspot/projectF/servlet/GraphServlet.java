package com.appspot.projectF.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GraphServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// いっかいきえたかなしみにまけててきとう
		String str = req.getParameter("pref");
		
		req.setAttribute("prefName", str);
		
		req.getRequestDispatcher("/graph.jsp").forward(req, resp);
	}
}
