package com.appspot.projectF.servlet;

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Climate {

	@PrimaryKey
	//都道府県名
	@Persistent
	private String prefectures;
	
	//年
	@Persistent
	private int year;
	
	//月
	@Persistent
	private int month;
	
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
		this.prefectures = prefectures;
		this.year = year;
		this.month = month;
		this.temperature = temperature;
		this.precipitation = precipitation;
		this.snowfall = snowfall;
		this.sunhour = sunhour;
	}

	// ゲッタとセッタ
	public String getPrefectures() {
		return prefectures;
	}

	public void setPrefectures(String prefectures) {
		this.prefectures = prefectures;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
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
