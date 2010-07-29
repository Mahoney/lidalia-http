package uk.org.lidalia.http.exception;

import uk.org.lidalia.http.Token;

public class IllegalTokenException extends uk.org.lidalia.RuntimeException {

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
