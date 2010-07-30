package uk.org.lidalia.http.api.exception;

public class InvalidResponseException extends uk.org.lidalia.RuntimeException {
	
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
