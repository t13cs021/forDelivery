package com.appspot.projectF.servlet;

import java.io.IOException;
import java.text.MessageFormat;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private static final String head = 
			"<html>\n"
			+ "<body>\n"
			+ " <span style=\"font-size: 200%\">農作物登録</span>\n"
			+ " <p>農作物名,月,最低温度,最高温度,最短日照時間,最長日照時間,メモ</p>"
			+ " <p>の形式で入力してください(従わないと大変なことになりますよ)</p>"
			+ " <p>カンマは半角です</p>"
			+ " <p>とりあえず簡易版なので，データにカンマを含めないでください(区切りだと勘違いします)</p>"
			+ "  <form action=\"/AddVeg\" method=\"post\">\n"
			+ "    <div>\n"
			+ "      <textarea name=\"content\" rows=\"2\" cols=\"40\"></textarea>"
			+ "    </div>\n"
			+ "    <input type=\"submit\" value=\"Submit\" />\n"
			+ "  </form>\n"; 
	
    // メモのテンプレート
    // {1}が本文
    private static final String cropsList = 
          "  <div>\n"
        + "    <pre>名前：{6}</pre> \n"
        + "    <pre>月{5}</pre> \n"
        + "    <pre>最低温度{4}</pre> \n"
        + "    <pre>最高温度{3}</pre> \n"
        + "    <pre>最短日照時間{2}</pre> \n"
        + "    <pre>最長日照時間{1}</pre> \n"
        + "    <pre>メモ：{0}</pre> \n"
        + "  </div> \n";
    
    // ページの最後
    private static final String tail = "  </body> \n" + "</html>\n";
    
    //Webページを作る
    private String render(List<Crops> crops) {
        StringBuffer sb = new StringBuffer();
        sb.append(head);
        for (Crops crop : crops)
            sb.append(MessageFormat.format(cropsList, crop.getName(), crop.getMonth(),
            		crop.getTemp_uLimit(), crop.getTemp_lLimit(), 
                    crop.getSunhour_uLimit(), crop.getSunhour_lLimit(), crop.getMemo()));
        sb.append(tail);
        return sb.toString();
    }
	
	
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
            //HTMLの出力
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(render(crops));
        } finally {
            if (pm != null && !pm.isClosed())
                pm.close();
        }
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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
