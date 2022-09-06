package io.specgen.minibus_booking;

public class Utils {

	public static String fixQuotes(String jsonStr) {
		return jsonStr.replaceAll("'", "\"");
	}
}
