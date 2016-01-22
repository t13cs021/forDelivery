package com.appspot.projectF.servlet;

import java.io.IOException;
import com.appspot.projectF.datastore.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCrops extends HttpServlet {
	//入力が空の時に代わりに入れる数値の定義
	public static float UDUMMY = 1000;
	public static float LDUMMY = -1000;
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PersistenceManager pm = null;
        //DB開けなかったら，開けてもデータストアを閉じる
        try {
            pm = PMF.get().getPersistenceManager();
            Query query = pm.newQuery(Crops.class);

            List<Crops> crops = (List<Crops>) query.execute();
            request.setAttribute("crops", crops);
        } finally {
            if (pm != null && !pm.isClosed())
                pm.close();
        }
        request.getRequestDispatcher("/addCrops.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//パラメータの文字コードを指定(しないと日本語が化ける)。
		req.setCharacterEncoding("UTF-8");

		//CSV形式のデータをパース
		//パース直後に各値を保持する一時変数
		String name,memo;
		int month;
		float temp_uLimit,temp_lLimit,sunhour_uLimit,sunhour_lLimit;
		//あとで一括でデータストアに投げるための農作物のリスト
		ArrayList<Crops> crops = new ArrayList<Crops>();
		//入力されたCSVを受け取る
		String content = req.getParameter("content");
		//行に分割して配列で保持
		String[] line = content.split("\n");
		//各行に対して処理
		for(int i=0;i<line.length;i++){
			//1行をカンマで区切ってそれぞれを文字列型配列に格納
			String[] field = line[i].split(",", -1);
			//カンマ区切りでデータを一つずつ一時変数に格納&必要なら型変換
			name = field[0];
			month = Integer.parseInt(field[1]);
			try{
				temp_lLimit = Float.parseFloat(field[2]);
			}
			catch(NumberFormatException e){
				temp_lLimit = LDUMMY;
			}
			try{
				temp_uLimit = Float.parseFloat(field[3]);
			}
			catch(NumberFormatException e){
				temp_uLimit = UDUMMY;
			}
			try{
				sunhour_lLimit = Float.parseFloat(field[4]);
			}
			catch(NumberFormatException e){
				sunhour_lLimit = LDUMMY;
			}
			try{
				sunhour_uLimit = Float.parseFloat(field[5]);
			}
			catch(NumberFormatException e){
				sunhour_uLimit = UDUMMY;
			}
			memo = field[6];
			//パースした各値を農作物オブジェクトにして，リストに追加
			crops.add(new Crops(name, month, temp_uLimit, temp_lLimit, sunhour_uLimit, sunhour_lLimit, memo));
        }

		//データストアへ投入
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			//データストアにリストを書き込み
			pm.makePersistentAll(crops);
		} finally {
			pm.close();
		}
 		resp.sendRedirect("/addcrops");
	}

}
