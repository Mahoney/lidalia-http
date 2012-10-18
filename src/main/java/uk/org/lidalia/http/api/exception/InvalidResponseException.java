package uk.org.lidalia.http.api.exception;

import uk.org.lidalia.lang.RichRuntimeException;

public class InvalidResponseException extends RichRuntimeException {

	private static final long serialVersionUID = 1L;

	private final String responseString;

	public InvalidResponseException(String responseString) {
		this(responseString, null);
	}

	public InvalidResponseException(String responseString, Throwable cause) {
		super("Unable to parse [" + responseString + "] into a valid HTTP Response", cause);
		this.responseString = responseString;
	}

	public String getResponseString() {
		return responseString;
	}

}
