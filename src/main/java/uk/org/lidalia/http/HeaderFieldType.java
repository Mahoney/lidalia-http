package uk.org.lidalia.http;

import java.nio.charset.CharacterCodingException;

public abstract class HeaderFieldType extends Token {

	public HeaderFieldType(String headerName) throws CharacterCodingException {
		super(headerName);
	}

	public Object parseValue(String headerValue) throws CharacterCodingException {
		return new Text(headerValue);
	}
}
