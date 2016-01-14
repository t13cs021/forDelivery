package com.appspot.projectF.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.projectF.datastore.*;

public class RecommendServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/***** 変数宣言 *****/
		//前のページで選択された都道府県名を取得
		String pref = req.getParameter("pref");
		//選出された野菜
		List<String> rank1_crops = new ArrayList<String>();
		String[] rank2_crops = {"foo", "bar"};
		//取得した気象データ
		List<Climate> climates;
		//取得した農作物データ
		List<Crops> crops;
		
		
		/***** データ取得 *****/
		PersistenceManager pm = null;
		Query query;
		//DB開けなかったら，開けてもデータストアを閉じる
		try {
			pm = PMF.get().getPersistenceManager();
			query = pm.newQuery(Climate.class);
			climates = (List<Climate>) query.execute();
			query = pm.newQuery(Crops.class);
			crops = (List<Crops>) query.execute();
		}
		finally {
			if (pm != null && !pm.isClosed())
				pm.close();
		}
		
		
		/***** 選出 *****/
		for(Crops crop:crops){
			//一旦テストとして全ての農作物を選出
			rank1_crops.add(crop.getName());
		}
		
		
		/***** jspに渡すデータ *****/
		//選択された都道府県名
		req.setAttribute("prefName", pref);
		// 選出された野菜一覧
		req.setAttribute("rank1_crops", rank1_crops);
		req.setAttribute("rank2_crops", rank2_crops);
		
		/***** ページ遷移 *****/
		req.getRequestDispatcher("/recommend.jsp").forward(req, resp);
	}
}
