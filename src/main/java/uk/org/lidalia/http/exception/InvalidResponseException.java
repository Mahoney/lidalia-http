package uk.org.lidalia.http.exception;

public class InvalidResponseException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidResponseException(String responseString) {
		this(responseString, null);
	}
	
	public InvalidResponseException(String responseString, Exception e) {
		super("Invalid response: " + responseString, e);
	}

}
