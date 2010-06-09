package uk.org.lidalia.http.headers;

import java.nio.charset.CharacterCodingException;

import uk.org.lidalia.http.Token;
import uk.org.lidalia.http.exception.IllegalHeaderValueException;

public abstract class HeaderFieldType extends Token {

	public HeaderFieldType(String headerName) throws CharacterCodingException {
		super(headerName);
	}

	public abstract HeaderFieldValue parseValue(String headerValue) throws IllegalHeaderValueException;
}
