package uk.org.lidalia.http.api.headerfield;

import java.nio.charset.CharacterCodingException;

import uk.org.lidalia.http.api.Text;
import uk.org.lidalia.http.api.exception.IllegalHeaderFieldValueException;
import uk.org.lidalia.http.api.exception.IllegalTokenException;

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
