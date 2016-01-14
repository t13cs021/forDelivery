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
		
		/***** 予測(平均計算) *****/
		//今年(西暦の数字)を取得
		Calendar calendar = Calendar.getInstance();
		int thisyear = calendar.get(Calendar.YEAR);
		//各数字の平均計算用一時変数
		float temperature,precipitation,snowfall,sunhour;
		//最近3年に絞る
		Iterator<Climate> itr = climates.iterator();
		while(itr.hasNext()){
			if( itr.next().getYear() < thisyear-4 ){
				itr.remove();
			}
		}
		//平均
		for(int i=1;i<=12;i++){
			//加算する変数初期化
			temperature=0;
			precipitation=0;
			snowfall=0;
			sunhour=0;
			//イテレータセット
			itr=climates.iterator();
			//3年*12ヶ月のデータを走査
			while(itr.hasNext()){
				//イテレータから取り出し
				Climate c=itr.next();
				//計算中の月かどうか
				if(c.getMonth() == i){
					//計算中の月なら加算
					temperature += c.getTemperature();
					precipitation += c.getPrecipitation();
					snowfall += c.getSnowfall();
					sunhour += c.getSunhour();
				}
			}
			//計算中の月のインスタンスを作り，3回加算されたデータを3で割り(平均を求め)代入
			forecast[i] = new Climate(pref,thisyear,i,temperature/3,precipitation/3,snowfall/3,sunhour/3);
		}
		
		/***** return *****/
		//12ヶ月それぞれの平均が入った配列を返却
		return forecast;
	}
}
