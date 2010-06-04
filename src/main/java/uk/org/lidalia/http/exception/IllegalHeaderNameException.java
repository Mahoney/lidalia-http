package uk.org.lidalia.http.exception;

public class IllegalHeaderNameException extends Exception {

	public IllegalHeaderNameException(Throwable cause) {
		super(cause);
	}

	public IllegalHeaderNameException(String message, Throwable cause) {
		super(message, cause);
	}

}
