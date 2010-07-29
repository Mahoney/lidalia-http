package uk.org.lidalia.http.headers;

import java.nio.charset.CharacterCodingException;

import uk.org.lidalia.http.Text;
import uk.org.lidalia.http.exception.IllegalHeaderFieldValueException;
import uk.org.lidalia.http.exception.IllegalTokenException;

class DefaultHeaderFieldName extends HeaderFieldName {

	DefaultHeaderFieldName(String headerName) throws IllegalTokenException {
		super(headerName);
	}

	@Override
	public HeaderFieldValue parseValue(String headerValue) throws IllegalHeaderFieldValueException {
		try {
			return new DefaultHeaderFieldValue(new Text(headerValue));
		} catch (CharacterCodingException e) {
			throw new IllegalHeaderFieldValueException(headerValue, this, e);
		}
	}
}
