package uk.org.lidalia.http.api.headerfield;

import uk.org.lidalia.http.api.Token;
import uk.org.lidalia.http.api.exception.IllegalHeaderFieldValueException;
import uk.org.lidalia.http.api.exception.IllegalTokenException;

public abstract class HeaderFieldName extends Token {

	public HeaderFieldName(String headerName) throws IllegalTokenException {
		super(headerName);
	}

	public abstract HeaderFieldValue parseValue(String headerValue) throws IllegalHeaderFieldValueException;
	
}
