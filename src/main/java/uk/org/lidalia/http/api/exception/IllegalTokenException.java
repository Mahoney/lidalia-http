package uk.org.lidalia.http.api.exception;

import uk.org.lidalia.http.api.Token;
import uk.org.lidalia.lang.RichRuntimeException;

public class IllegalTokenException extends RichRuntimeException {

    private static final long serialVersionUID = 1L;

    private final String tokenString;

    public IllegalTokenException(String tokenString) {
        super(makeMessage(tokenString));
        this.tokenString = tokenString;
    }

    public IllegalTokenException(String tokenString, Throwable cause) {
        super(makeMessage(tokenString), cause);
        this.tokenString = tokenString;
    }

    private static String makeMessage(String tokenString) {
        return "[" + tokenString + "] is not a valid Token - must match " + Token.LEGAL_CHARS_STRING;
    }

    public String getTokenString() {
        return tokenString;
    }

}
