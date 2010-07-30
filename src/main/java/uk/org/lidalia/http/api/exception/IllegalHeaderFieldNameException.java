package uk.org.lidalia.http.api.exception;

public class IllegalHeaderFieldNameException extends uk.org.lidalia.RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private final String headerFieldName;
	
	public IllegalHeaderFieldNameException(String headerFieldName) {
		this(headerFieldName, null);
	}

	public IllegalHeaderFieldNameException(String headerFieldName, Throwable cause) {
		super(headerFieldName + " is not a legal header field name", cause);
		this.headerFieldName = headerFieldName;
	}
	
	public String getHeaderFieldName() {
		return headerFieldName;
	}

}
