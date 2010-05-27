package uk.org.lidalia.http;

import java.nio.charset.CharacterCodingException;

public class UnknownHeaderFieldType extends HeaderFieldType<Text> {

	public UnknownHeaderFieldType(String headerName) throws CharacterCodingException {
		super(headerName);
	}

	@Override
	public Text parseValue(String headerValue) {
		try {
			return new Text(headerValue);
		} catch (CharacterCodingException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
