package uk.org.lidalia.http.exception;

public class IllegalHeaderFieldValueException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public IllegalHeaderFieldValueException(Throwable cause) {
		super(cause);
	}

	public IllegalHeaderFieldValueException(String message, Exception cause) {
		super(message, cause);
	}

}
