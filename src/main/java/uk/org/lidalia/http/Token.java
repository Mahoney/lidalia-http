package uk.org.lidalia.http;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.lang.Validate;

public class Token extends StringWrapper {
	
	private static final int DEL = 127;
	private static final int LAST_CONTROL_CHARACTER = 31;
	private static final byte[] SEPARATORS = {
		'(', ')', '<', '>',  '@',
		',', ';', ':', '\\', '"',
		'/', '[', ']', '?',  '=',
		'{', '}', ' ', '\t'
		};
	private static final String SEPARATORS_STRING = new String(SEPARATORS, Charset.forName("ISO-8859-1"));
	
	static {
		Arrays.sort(SEPARATORS);
	}

	public Token(String token) {
		super(token);
		byte[] textAsBytes = token.getBytes(Charset.forName("ISO-8859-1"));
		Validate.isTrue(textAsBytes.length > 0, "Token must contain at least one character");
		for (int i = 0; i < textAsBytes.length; i++) {
			byte currentChar = textAsBytes[i];
			Validate.isTrue(notAControlCharacter(currentChar), "[" + token + "] is not a valid Token - contains [" + currentChar + "], must only contain non CTL US-ASCII characters");
			Validate.isTrue(notASeparator(currentChar), "[" + token + "] is not a valid Token - contains [" + currentChar + "], must not contain any of the characters " + SEPARATORS_STRING);
		}
	}

	private boolean notAControlCharacter(byte currentChar) {
		return currentChar > LAST_CONTROL_CHARACTER && currentChar < DEL;
	}

	private boolean notASeparator(byte currentChar) {
		return Arrays.binarySearch(SEPARATORS, currentChar) < 0;
	}
}
