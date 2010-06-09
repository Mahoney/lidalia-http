package uk.org.lidalia.http.immutable;

import java.util.Collections;
import java.util.Iterator;


import uk.org.lidalia.http.AbstractHeaderFields;
import uk.org.lidalia.http.HeaderField;
import uk.org.lidalia.http.exception.IllegalHeaderNameException;
import uk.org.lidalia.http.exception.IllegalHeaderValueException;

public final class HeaderFields extends AbstractHeaderFields {
	
	public HeaderFields(String headersString) throws IllegalHeaderNameException, IllegalHeaderValueException {
		super(headersString);
	}

	public HeaderFields(HeaderField... newHeaders) throws IllegalHeaderNameException, IllegalHeaderValueException {
		super(newHeaders);
	}

	@Override
	public Iterator<HeaderField> iterator() {
		return Collections.unmodifiableCollection(headers.values()).iterator();
	}
}
