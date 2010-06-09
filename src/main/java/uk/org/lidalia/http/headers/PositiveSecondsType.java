package uk.org.lidalia.http.headers;

import java.nio.charset.CharacterCodingException;

import org.joda.time.Seconds;

import uk.org.lidalia.http.exception.IllegalHeaderValueException;
import uk.org.lidalia.http.response.ResponseHeaderFieldType;

public class PositiveSecondsType extends ResponseHeaderFieldType {

	public PositiveSecondsType(String headerName) throws CharacterCodingException {
		super(headerName);
	}

	@Override
	public PositiveSeconds parseValue(String headerValue) throws IllegalHeaderValueException {
		return new PositiveSeconds(Seconds.seconds(Integer.parseInt(headerValue)));
	}

}
