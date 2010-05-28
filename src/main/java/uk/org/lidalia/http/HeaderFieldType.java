package uk.org.lidalia.http;

import java.nio.charset.CharacterCodingException;

public abstract class HeaderFieldType extends Token {

	public HeaderFieldType(String headerName) throws CharacterCodingException {
		super(headerName);
	}

	public abstract Object parseValue(String headerValue);
}
