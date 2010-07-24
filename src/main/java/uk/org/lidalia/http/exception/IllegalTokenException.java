package uk.org.lidalia.http.exception;

public class IllegalTokenException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalTokenException() {
		super();
	}

	public IllegalTokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalTokenException(String message) {
		super(message);
	}

	public IllegalTokenException(Throwable cause) {
		super(cause);
	}
}
