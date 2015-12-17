package com.appspot.projectF.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecommendServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// いっかいきえたかなしみにまけててきとう
		String str = req.getParameter("pref");
		
		/*
		 * このへんでDBとかアクセスしていろいろ算出してsetAttributeしてあげる
		 * ほかのServletも同じ流れ
		 */
		
		req.setAttribute("prefName", str);
		
		// sample
		String[] mozis = {"hoge", "fuga", "foo", "bar"};
		req.setAttribute("mozis", mozis);
		
		req.getRequestDispatcher("/recommend.jsp").forward(req, resp);
	}
}
