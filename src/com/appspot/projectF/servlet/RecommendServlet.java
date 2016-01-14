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
		/***** 変数宣言 *****/
		//前のページで選択された都道府県名を取得
		String pref = req.getParameter("pref");
		//選出された野菜
		String[] rank1_crops = {"hoge", "fuga"};
		String[] rank2_crops = {"foo", "bar"};
		
		/***** データ取得 *****/
		
		
		
		
		
		/***** 選出 *****/
		/*
		 * このへんでDBとかアクセスしていろいろ算出してsetAttributeしてあげる
		 * ほかのServletも同じ流れ
		 */
		
		/***** 以下，jspに渡すデータ *****/
		//選択された都道府県名
		req.setAttribute("prefName", pref);
		// 選出された野菜一覧
		req.setAttribute("rank1_crops", rank1_crops);
		req.setAttribute("rank2_crops", rank2_crops);
		
		/***** ページ遷移 *****/
		req.getRequestDispatcher("/recommend.jsp").forward(req, resp);
	}
}
