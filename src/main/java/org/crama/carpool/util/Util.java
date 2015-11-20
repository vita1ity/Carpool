package org.crama.carpool.util;

import java.time.LocalTime;

public class Util {
	public static LocalTime createTime(String timeStr) {
		if (timeStr != null && !timeStr.equals("")) {
			String[] num = timeStr.split(":");
			
			int hours = Integer.parseInt(num[0]);
			int minutes = Integer.parseInt(num[1]);
			
			LocalTime timeLocal = LocalTime.of(hours, minutes);
			return timeLocal;
		}
		else return null;
	}
}
