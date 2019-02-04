package tools;

import java.util.regex.Pattern;

public class Validation {
	public static final Pattern patternEmail = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
	public static final Pattern patternTelFixeBE = Pattern.compile("[0-9]{2,3}/[0-9]{7,8}");
	public static final Pattern patternTelGsm = Pattern.compile("[0-9]{3,4}/[0-9]{6,7}");

	public static boolean isEmailValid(String elem) {
		return elem==null?false:patternEmail.matcher(elem).matches();
	}

	public static boolean isTelFixeValid(String elem) {
		return elem==null?false:patternTelFixeBE.matcher(elem).matches();
	}

	public static boolean isTelGsmValid(String elem) {
		return elem==null?false:patternTelGsm.matcher(elem).matches();
	}
}
