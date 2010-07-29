package uk.org.lidalia.http.exception;

import uk.org.lidalia.http.headers.HeaderFieldName;

public class IllegalHeaderFieldValueException extends uk.org.lidalia.RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private final String headerFieldValue;
	private final HeaderFieldName headerFieldName;
	
	public IllegalHeaderFieldValueException(String headerFieldValue, HeaderFieldName headerFieldName) {
		this(headerFieldValue, headerFieldName, null);
	}

	public IllegalHeaderFieldValueException(String headerFieldValue,  HeaderFieldName headerFieldName, Throwable cause) {
		super(headerFieldValue + " is not a legal header field value for " + headerFieldName, cause);
		this.headerFieldValue = headerFieldValue;
		this.headerFieldName = headerFieldName;
	}
	
	public String getHeaderFieldValue() {
		return headerFieldValue;
	}

	public HeaderFieldName getHeaderFieldName() {
		return headerFieldName;
	}
}
