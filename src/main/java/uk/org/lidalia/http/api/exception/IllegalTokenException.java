package uk.org.lidalia.http.api.exception;

import uk.org.lidalia.http.api.Token;

public class IllegalTokenException extends uk.org.lidalia.lang.RichRuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final String tokenString;
	
	public IllegalTokenException(String tokenString) {
		this(tokenString, null);
	}

	public IllegalTokenException(String tokenString, Throwable cause) {
		super("[" + tokenString + "] is not a valid Token - must match " + Token.LEGAL_CHARS_STRING, cause);
		this.tokenString = tokenString;
	}

	public String getTokenString() {
		return tokenString;
	}
	
}
