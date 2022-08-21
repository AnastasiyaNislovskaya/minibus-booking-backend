package io.specgen.minibus_booking_backend;

public class Utils {
	final static String ADMIN_EMAIL = "nislovskaya96@gmail.com";
	final static String USER_EMAIL = "alexandrova@mail.ru";

	public static String fixQuotes(String jsonStr) {
		return jsonStr.replaceAll("'", "\"");
	}
}
