package uk.org.lidalia.http.headers;

import org.joda.time.Seconds;

import uk.org.lidalia.http.exception.IllegalHeaderFieldValueException;
import uk.org.lidalia.http.exception.IllegalTokenException;

public class PositiveSecondsHeaderFieldName extends HeaderFieldName {

	public PositiveSecondsHeaderFieldName(String headerName) throws IllegalTokenException {
		super(headerName);
	}

	@Override
	public PositiveSeconds parseValue(String headerValue) throws IllegalHeaderFieldValueException {
		return new PositiveSeconds(Seconds.seconds(Integer.parseInt(headerValue)));
	}

}
