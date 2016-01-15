package com.appspot.projectF.servlet;

import java.io.IOException;
import java.util.*;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.projectF.datastore.*;
import com.appspot.projectF.util.Forecast;

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
		//取得した農作物データ
		List<Crops> crops;
		//取得した気象予測データ
		Climate[] climate;
		
		/***** 農作物データ取得 *****/
		PersistenceManager pm = null;
		//DB開けなかったら，開けてもデータストアを閉じる
		try {
			pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(Crops.class);
			crops = (List<Crops>) query.execute();
		}
		finally {
			if (pm != null && !pm.isClosed())
				pm.close();
		}
		
		/***** 気象予測データ取得 *****/
		Forecast forecast = new Forecast();
		climate=forecast.getForecast(pref);
		
		
		/***** 選出 *****/
		for(Crops crop:crops){
			//作物情報を作物名ごとに分類
			//すべての農作物について適合するか確認
			//rank1_crops,rank2_cropsに格納
			
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
