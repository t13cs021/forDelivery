package com.appspot.projectF.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.projectF.datastore.Climate;
import com.appspot.projectF.util.Forecast;

public class GraphServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//県名の取得と受け渡し
		String pref = req.getParameter("pref");
		req.setAttribute("prefName", pref);

		//気象予測データの取得
		// 気象データ予測関数を使うためのアレ
		Forecast forecast = new Forecast();
		// 予測関数で返ってきた配列をいれるやつ
		Climate [] prefClim = forecast.getForecast(pref);
		req.setAttribute("climate", prefClim);

		//jspへ受け渡し
		req.getRequestDispatcher("/graph.jsp").forward(req, resp);
	}
}
