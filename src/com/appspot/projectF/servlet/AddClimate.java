package com.appspot.projectF.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddClimate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PersistenceManager pm = null;
        //DB開けなかったら，開けてもデータストアを閉じる
        try {
            pm = PMF.get().getPersistenceManager();
            Query query = pm.newQuery(Climate.class);

            List<Climate> climates = (List<Climate>) query.execute();
            request.setAttribute("climate", climates);
        } finally {
            if (pm != null && !pm.isClosed())
                pm.close();
        }
        request.getRequestDispatcher("/addClimate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//パラメータの文字コードを指定(しないと日本語が化ける)。
		req.setCharacterEncoding("UTF-8");

		//CSV形式のデータをパース
		//パース直後に各値を保持する一時変数
		String prefectures;
		int year,month;
		float temperature,precipitation,snowfall,sunhour;
		//あとで一括でデータストアに投げるための気候のリスト
		ArrayList<Climate> climates = new ArrayList<Climate>();
		//パースに必要
		StringTokenizer st;
		//入力されたCSVを受け取る
		String content = req.getParameter("content");
		//行に分割して配列で保持
		String[] line = content.split("\n");
		//各行に対して処理
		for(int i=0;i<line.length;i++){
			//パースする
			st = new StringTokenizer(line[i],",");
			//カンマ区切りでデータを一つずつ変数に格納&型変換
			prefectures = st.nextToken();
			year = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			temperature = Float.parseFloat(st.nextToken());
			precipitation = Float.parseFloat(st.nextToken());
			snowfall = Float.parseFloat(st.nextToken());
			sunhour = Float.parseFloat(st.nextToken());
			//パースした各値を気候オブジェクトにして，リストに追加
			climates.add(new Climate(prefectures, year, month, temperature, precipitation, snowfall, sunhour));
        }

		//データストアへ投入
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			//データストアにリストを書き込み
			pm.makePersistentAll(climates);
		} finally {
			pm.close();
		}
 		resp.sendRedirect("/addclimate");
	}

}
