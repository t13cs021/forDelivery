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



/**
 * Servlet implementation class AddVeg
 */
public class AddVeg extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddVeg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PersistenceManager pm = null;
        //DB開けなかったら，開けてもデータストアを閉じる
        try {
            pm = PMF.get().getPersistenceManager();
            Query query = pm.newQuery(Crops.class);
            //query.setOrdering("date desc");

            List<Crops> crops = (List<Crops>) query.execute();
            request.setAttribute("crops", crops);
        } finally {
            if (pm != null && !pm.isClosed())
                pm.close();
        }
        request.getRequestDispatcher("/addVeg.jsp").forward(request, response);
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
			name = st.nextToken();
			month = Integer.parseInt(st.nextToken());
			temp_uLimit = Float.parseFloat(st.nextToken());
			temp_lLimit = Float.parseFloat(st.nextToken());
			sunhour_uLimit = Float.parseFloat(st.nextToken());
			sunhour_lLimit = Float.parseFloat(st.nextToken());
			memo = st.nextToken();
			//パースした各値を農作物オブジェクトにして，リストに追加
			crops.add(new Crops(name, month, temp_uLimit, temp_lLimit, sunhour_uLimit, sunhour_lLimit, memo));
        }

		//データストアへ投入
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			//データストアにリストを書き込み(これでできるか不明)
			pm.makePersistentAll(crops);
		} finally {
			pm.close();
		}
 		resp.sendRedirect("/AddVeg");
	}

}
