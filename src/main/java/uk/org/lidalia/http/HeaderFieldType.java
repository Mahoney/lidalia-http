package uk.org.lidalia.http;

import java.nio.charset.CharacterCodingException;

public abstract class HeaderFieldType<E> extends Token {

	public HeaderFieldType(String headerName) throws CharacterCodingException {
		super(headerName);
	}

	public abstract E parseValue(String headerValue);
}
