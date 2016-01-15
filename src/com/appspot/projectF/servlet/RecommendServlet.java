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
		List<String> rank2_crops = new ArrayList<String>();
		String[] debug_crops = {"foo", "bar"};
		//取得した農作物データ
		List<Crops> crops;
		//取得した気象予測データ
		Climate[] climates;
		
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
		climates=forecast.getForecast(pref);
		//デバッグ用表示
		for(int i=1;i<13;i++){
			System.out.println(climates[i].getPrefectures() + "," + climates[i].getYear() + "," + climates[i].getMonth() + "::" + climates[i].getTemperature() + "," + climates[i].getSunhour());
		}
		
		/***** 選出 *****/
		Map<String, List<Crops>> cropMap = getMapWithName(crops);
		for (List<Crops> l: cropMap.values()) {
			int items = l.size();
			if (items == 0) return;
			String vegName = l.get(0).getName();
			int tempcount = 0, suncount = 0;
			for (Crops c: l) {
				int month = c.getMonth();
				float forecastTemp = climates[month].getTemperature();
				float forecastSun  = climates[month].getSunhour();
				if (c.getTemp_lLimit() <= forecastTemp && forecastTemp <= c.getTemp_uLimit()) tempcount++;
				if (c.getSunhour_lLimit() <= forecastSun && forecastSun <= c.getSunhour_uLimit()) suncount++;
			}
			int result = (tempcount == items ? 1 : 0) + (suncount == items ? 1 : 0);
			System.out.println(vegName + "'s result is " + result + "(" + tempcount + "," + suncount + ")");
			if (result == 2) {
				rank2_crops.add(vegName);
			} else if (result == 1) {
				rank1_crops.add(vegName);
			}
		}
		
		
		/***** jspに渡すデータ *****/
		//選択された都道府県名
		req.setAttribute("prefName", pref);
		// 選出された野菜一覧
		req.setAttribute("rank1_crops", rank1_crops);
		req.setAttribute("rank2_crops", rank2_crops);
		req.setAttribute("debug_crops", debug_crops);
		
		/***** ページ遷移 *****/
		req.getRequestDispatcher("/recommend.jsp").forward(req, resp);
	}

	private static Map<String, List<Crops>> getMapWithName(List<Crops> allCrops) {
		Set<String> nameSet = new HashSet<String>();
		
		for (Crops c: allCrops) nameSet.add(c.getName());
		
		Map<String, List<Crops>> map = new HashMap<String, List<Crops>>();
		
		for (String vegName: nameSet) map.put(vegName, new LinkedList<Crops>());
		
		for (Crops c: allCrops) {
			map.get(c.getName()).add(c);
		}
		
		return map;
	}
}
