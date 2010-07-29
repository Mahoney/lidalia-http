package uk.org.lidalia.http;

import java.util.regex.Pattern;

import uk.org.lidalia.WrappedString;
import uk.org.lidalia.http.exception.IllegalTokenException;

public class Token extends WrappedString {
	
	public static final String LEGAL_CHARS_STRING = "[a-zA-Z0-9!#\\$%&'\\*\\+\\-\\.\\^_`\\|~]+";
	private static final Pattern LEGAL_CHARS = Pattern.compile(LEGAL_CHARS_STRING);
	
	public Token(String token) throws IllegalTokenException {
		super(token);
		if (!LEGAL_CHARS.matcher(token).matches()) {
			throw new IllegalTokenException(token);
		}
	}
}
