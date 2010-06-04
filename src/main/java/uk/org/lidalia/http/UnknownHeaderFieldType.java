package uk.org.lidalia.http;

import java.nio.charset.CharacterCodingException;

import uk.org.lidalia.http.exception.IllegalHeaderValueException;

class UnknownHeaderFieldType extends HeaderFieldType {

	UnknownHeaderFieldType(String headerName) throws CharacterCodingException {
		super(headerName);
	}

	@Override
	public HeaderFieldValue parseValue(String headerValue) throws IllegalHeaderValueException {
		try {
			return new DefaultHeaderFieldValue(new Text(headerValue));
		} catch (CharacterCodingException e) {
			throw new IllegalHeaderValueException(e);
		}
	}
}
