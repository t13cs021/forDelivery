package com.appspot.projectF.servlet;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Climate {

	@PrimaryKey
	//都道府県名:年:月
	@Persistent
	private String prefectures;


	//気温
	@Persistent
	private float temperature;

	//降水量
	@Persistent
	private float precipitation;

	//降雪量
	@Persistent
	private float snowfall;

	//日照時間
	@Persistent
	private float sunhour;

	public Climate(String prefectures, int year, int month, float temperature,
			float precipitation, float snowfall, float sunhour) {
		super();
		this.prefectures = prefectures + ":" + year + ":"+ month;
		this.temperature = temperature;
		this.precipitation = precipitation;
		this.snowfall = snowfall;
		this.sunhour = sunhour;
	}

	// ゲッタとセッタ
	public String getPrefectures() {
		return prefectures.split(":")[0];
	}

	public void setPrefectures(String prefectures) {
		String[] strs = this.prefectures.split(":");
		strs[0] = prefectures;
		this.prefectures = strs[0] + ":" + strs[1] + ":" + strs[2];
	}

	public int getYear() {
		return Integer.parseInt(prefectures.split(":")[1]);
	}

	public void setYear(int year) {
		String[] strs = this.prefectures.split(":");
		strs[1] = String.valueOf(year);
		this.prefectures = strs[0] + ":" + strs[1] + ":" + strs[2];
	}

	public int getMonth() {
		return Integer.parseInt(prefectures.split(":")[2]);
	}

	public void setMonth(int month) {
		String[] strs = this.prefectures.split(":");
		strs[2] = String.valueOf(month);
		this.prefectures = strs[0] + ":" + strs[1] + ":" + strs[2];
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(float precipitation) {
		this.precipitation = precipitation;
	}

	public float getSnowfall() {
		return snowfall;
	}

	public void setSnowfall(float snowfall) {
		this.snowfall = snowfall;
	}

	public float getSunhour() {
		return sunhour;
	}

	public void setSunhour(float sunhour) {
		this.sunhour = sunhour;
	}

}
