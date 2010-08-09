package uk.org.lidalia.lang;

public class Exception extends java.lang.Exception {

	private static final long serialVersionUID = 1L;

	public Exception() {
		super();
	}

	public Exception(String message) {
		super(message);
	}

	public Exception(Throwable cause) {
		super(cause);
	}

	public Exception(String message, Throwable cause) {
		super(message, cause);
	}
	
	@Override
	public String toString() {
		return Utils.throwableToString(super.toString(), getCause());
	}

}
