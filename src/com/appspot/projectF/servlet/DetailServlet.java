package com.appspot.projectF.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// いっかいきえたかなしみにまけててきとう
		String str = req.getParameter("pref");
		String veg = req.getParameter("yasai");
		
		req.setAttribute("prefName", str);
		req.setAttribute("vegName", veg);
		
		req.getRequestDispatcher("/detail.jsp").forward(req, resp);
	}
}
