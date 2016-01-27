package com.appspot.projectF.util;

import java.util.*;

public class CropMap {
	private static final Map<String, String> map;
	static {
		map = new HashMap<String, String>();
		map.put("白菜", "hakusai");
		map.put("ナス", "nasu");
		map.put("ぶどう", "budou");
		map.put("大根", "daikon");
		map.put("にんじん", "ninzin");
		map.put("じゃがいも", "jagaimo");
		map.put("レタス", "retasu");
		map.put("たまねぎ", "tamanegi");
		map.put("みかん", "mikan");
		map.put("オリーブ", "ori-bu");
		map.put("カカオ", "kakao");
		map.put("桃", "momo");
	}
	public static String getRorm(String origName) {
		return map.get(origName);
	}
}
