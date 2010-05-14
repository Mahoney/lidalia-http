package uk.org.lidalia.http.exception;

public class InvalidResponseException extends Exception {

	public InvalidResponseException(String responseString) {
		this(responseString, null);
	}
	
	public InvalidResponseException(String responseString, Exception e) {
		super("Invalid response: " + responseString, e);
	}

}
