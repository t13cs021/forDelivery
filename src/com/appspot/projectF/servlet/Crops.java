package com.appspot.projectF.servlet;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Crops {

	@PrimaryKey
	//農作物:月
	@Persistent
	private String name;

	//気温上限
	@Persistent
	private float temp_uLimit;

	//気温下限
	@Persistent
	private float temp_lLimit;

	//日照時間上限
	@Persistent
	private float sunhour_uLimit;

	//日照時間下限
	@Persistent
	private float sunhour_lLimit;

	//メモ
	@Persistent
	private String memo;

	public Crops(String name, int month, float temp_uLimit, float temp_lLimit,
			float sunhour_uLimit, float sunhour_lLimit, String memo) {
		super();
		this.name = name + ":" + month;
		this.temp_uLimit = temp_uLimit;
		this.temp_lLimit = temp_lLimit;
		this.sunhour_uLimit = sunhour_uLimit;
		this.sunhour_lLimit = sunhour_lLimit;
		this.memo = memo;
	}

	//ゲッタ, セッタ
	public String getName() {
		return name.split(":")[0];
	}

	public void setName(String name) {
		String[] strs = this.name.split(":");
		strs[0] = name;
		this.name = String.join(":", strs);
	}

	public int getMonth() {
		return Integer.getInteger(name.split(":")[1]);
	}

	public void setMonth(int month) {
		String[] strs = this.name.split(":");
		strs[1] = String.valueOf(month);
		this.name = String.join(":", strs);
	}

	public float getTemp_uLimit() {
		return temp_uLimit;
	}

	public void setTemp_uLimit(float temp_uLimit) {
		this.temp_uLimit = temp_uLimit;
	}

	public float getTemp_lLimit() {
		return temp_lLimit;
	}

	public void setTemp_lLimit(float temp_lLimit) {
		this.temp_lLimit = temp_lLimit;
	}

	public float getSunhour_uLimit() {
		return sunhour_uLimit;
	}

	public void setSunhour_uLimit(float sunhour_uLimit) {
		this.sunhour_uLimit = sunhour_uLimit;
	}

	public float getSunhour_lLimit() {
		return sunhour_lLimit;
	}

	public void setSunhour_lLimit(float sunhour_lLimit) {
		this.sunhour_lLimit = sunhour_lLimit;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


}
