package uk.org.lidalia.http.exception;

public class IllegalHeaderValueException extends Exception {

	public IllegalHeaderValueException(Throwable cause) {
		super(cause);
	}

	public IllegalHeaderValueException(String message, Exception cause) {
		super(message, cause);
	}

}
