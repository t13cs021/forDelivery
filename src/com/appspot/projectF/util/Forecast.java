package com.appspot.projectF.util;

import java.util.*;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.appspot.projectF.datastore.Climate;
import com.appspot.projectF.datastore.PMF;

public class Forecast {

	public Climate[] getForecast(String pref){
		/***** 変数宣言 *****/
		//予測データ12ヶ月分を入れる配列(0は空)
		Climate[] forecast = new Climate[13];
		//取得した気象データ
		List<Climate> climates;
		//今年(西暦の数字)を取得
		Calendar calendar = Calendar.getInstance();
		int thisyear = calendar.get(Calendar.YEAR);
		
		/***** 気象データ取得 *****/
		PersistenceManager pm = null;
		//DB開けなかったら，開けてもデータストアを閉じる
		try {
			pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(Climate.class);
			climates = (List<Climate>) query.execute();
		}
		finally {
			if (pm != null && !pm.isClosed())
				pm.close();
		}
		
		/****** 気象データから必要なデータだけを抽出(県名，直近3年) *****/
		//絞るためのイテレータ宣言
		Iterator<Climate> itr = climates.iterator();
		//イテレータで取り出したデータの一時格納用変数
		Climate cl;
		//県名と年で絞る
		while(itr.hasNext()){
			cl = itr.next();
			if( !cl.getPrefectures().equals(pref) || cl.getYear() < thisyear-3 ){
				itr.remove();
			}
		}

		/***** 予測(平均計算) *****/
		//加算用変数群(割られる数)
		float[] temperatures = new float[13];
		float[] precipitations = new float[13];
		float[] snowfalls = new float[13];
		float[] sunhours = new float[13];
		//割り算用変数(割る数)
		int[] sumcount = new int[13];
		//配列初期化
		for(int i=0;i<13;i++){
			temperatures[i] = precipitations[i] = snowfalls[i] = sunhours[i] = sumcount[i] = 0;
		}
		//今見ている気象データが何月のデータかを格納しておく変数(代入先の配列の添字になる)
		int month;
		//絞った予測データを走査し，割る数割られる数を加算していく
		for(Climate c:climates){
			month = c.getMonth();
			temperatures[month] += c.getTemperature();
			precipitations[month] += c.getPrecipitation();
			snowfalls[month] += c.getSnowfall();
			sunhours[month] += c.getSunhour();
			sumcount[month]++;
		}
		//各月のインスタンスを作って返却する配列に格納
		for(int i=1;i<=12;i++){
			//加算されたデータを年数で割り(平均を求め)，コンストラクタに渡す
			forecast[i] = new Climate(pref,thisyear,i,temperatures[i]/sumcount[i],
					precipitations[i]/sumcount[i],snowfalls[i]/sumcount[i],sunhours[i]/sumcount[i]);
		}
		
		/***** return *****/
		//12ヶ月それぞれの平均が入った配列を返却
		return forecast;
	}
}
