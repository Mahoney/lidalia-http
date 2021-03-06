package uk.org.lidalia.http.api.exception;

import uk.org.lidalia.lang.RichRuntimeException;

public class InvalidHeaderException extends RichRuntimeException {

    private static final long serialVersionUID = 1L;

    private final String headerString;

    public InvalidHeaderException(String headerString) {
        this(headerString, null);
    }

    public InvalidHeaderException(String headerString, Exception e) {
        super("Unable to parse [" + headerString + "] into a valid HTTP Header", e);
        this.headerString = headerString;
    }

    public String getHeaderString() {
        return headerString;
    }
}
