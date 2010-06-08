package uk.org.lidalia.http;

import java.nio.charset.CharacterCodingException;
import java.util.regex.Pattern;

import org.apache.commons.lang.Validate;

import uk.org.lidalia.StringWrapper;

public class Token extends StringWrapper {
	
	private static final String LEGAL_CHARS_STRING = "[a-zA-Z0-9!#\\$%&'\\*\\+\\-\\.\\^_`\\|~]+";
	private static final Pattern LEGAL_CHARS = Pattern.compile(LEGAL_CHARS_STRING);
	
	public Token(String token) throws CharacterCodingException {
		super(token);
		Validate.isTrue(LEGAL_CHARS.matcher(token).matches(), "[" + token + "] is not a valid Token - must match " + LEGAL_CHARS_STRING);
	}
}
