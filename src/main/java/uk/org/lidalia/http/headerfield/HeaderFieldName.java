package uk.org.lidalia.http.headerfield;

import uk.org.lidalia.http.Token;
import uk.org.lidalia.http.exception.IllegalHeaderFieldValueException;
import uk.org.lidalia.http.exception.IllegalTokenException;

public abstract class HeaderFieldName extends Token {

	public HeaderFieldName(String headerName) throws IllegalTokenException {
		super(headerName);
	}

	public abstract HeaderFieldValue parseValue(String headerValue) throws IllegalHeaderFieldValueException;
	
}
