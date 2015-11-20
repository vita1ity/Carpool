package org.crama.carpool.validation;

public class RouteValidator {
	private static final String WRONG_TIME_PATTERN = "Wrong time pattern. Correct is hours:minutes";
	private static final String WRONG_INT_PATTERN = "Wrong format for number of passengers. Should be integer value greater then 0";
	
	public static String validateRoute(String sourceAddress, String destinationAddress, String startTime,
			String endTime, String numOfPassengers) {
		
		String[] startNum = startTime.split(":");
		String[] endNum = endTime.split(":");
		if (startNum.length != 2 || endNum.length != 2) {
			return WRONG_TIME_PATTERN;
		}
		else {
			try {
				int startHours = Integer.parseInt(startNum[0]);
				int endHours = Integer.parseInt(endNum[0]);
				int startMinutes = Integer.parseInt(startNum[1]);
				int endMinutes = Integer.parseInt(endNum[1]);
				
				if (startHours < 0 || startHours > 23 || endHours < 0 || endHours > 23 ||
					startMinutes < 0 || startMinutes > 59 || endMinutes < 0 || endMinutes > 59) {
					return WRONG_TIME_PATTERN;
				}
			}
			catch (NumberFormatException e) {
				return WRONG_TIME_PATTERN;
			}
			
		}
		
		try {
			int numPass = Integer.parseInt(numOfPassengers);
			if (numPass < 1) {
				return WRONG_INT_PATTERN;
			}
		}
		catch (NumberFormatException e) {
			return WRONG_INT_PATTERN;
		}
		
		return null;
	}

}
