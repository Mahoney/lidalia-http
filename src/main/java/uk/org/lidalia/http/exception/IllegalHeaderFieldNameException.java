package uk.org.lidalia.http.exception;

public class IllegalHeaderFieldNameException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public IllegalHeaderFieldNameException(Throwable cause) {
		super(cause);
	}

	public IllegalHeaderFieldNameException(String message, Throwable cause) {
		super(message, cause);
	}

}
