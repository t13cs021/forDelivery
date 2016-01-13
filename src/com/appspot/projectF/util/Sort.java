package com.appspot.projectF.util;

import java.util.*;

import com.appspot.projectF.datastore.*;

public class Sort {
	public static void sortClimateForMonth(List<Climate> list) {
		Collections.sort(list, new Comparator<Climate>() {
			@Override
			public int compare(Climate c1, Climate c2) {
				return c1.getMonth() - c2.getMonth();
			}
		});
	}
	public static void sortCropsForMonth(List<Crops> list) {
		Collections.sort(list, new Comparator<Crops>() {
			@Override
			public int compare(Crops c1, Crops c2) {
				return c1.getMonth() - c2.getMonth();
			}
		});
	}
}
