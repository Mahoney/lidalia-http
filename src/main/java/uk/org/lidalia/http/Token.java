package uk.org.lidalia.http;

import java.util.regex.Pattern;

import uk.org.lidalia.StringWrapper;
import uk.org.lidalia.http.exception.IllegalTokenException;

public class Token extends StringWrapper {
	
	private static final String LEGAL_CHARS_STRING = "[a-zA-Z0-9!#\\$%&'\\*\\+\\-\\.\\^_`\\|~]+";
	private static final Pattern LEGAL_CHARS = Pattern.compile(LEGAL_CHARS_STRING);
	
	public Token(String token) throws IllegalTokenException {
		super(token);
		if (!LEGAL_CHARS.matcher(token).matches()) {
			throw new IllegalTokenException("[" + token + "] is not a valid Token - must match " + LEGAL_CHARS_STRING);
		}
	}
}
