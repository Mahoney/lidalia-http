package uk.org.lidalia.http;

import java.nio.charset.CharacterCodingException;

public class UnknownHeaderFieldType extends Token implements HeaderFieldType {

	public UnknownHeaderFieldType(String headerName) throws CharacterCodingException {
		super(headerName);
	}

	@Override
	public String getName() {
		return toString();
	}

	@Override
	public Object parseValue(String headerValue) throws CharacterCodingException {
		return new Text(headerValue);
	}
}
