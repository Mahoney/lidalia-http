package uk.org.lidalia.http.immutable;

import java.util.Collections;
import java.util.Iterator;


import uk.org.lidalia.http.HeaderField;
import uk.org.lidalia.http.exception.IllegalHeaderFieldNameException;
import uk.org.lidalia.http.exception.IllegalHeaderFieldValueException;
import uk.org.lidalia.http.headers.AbstractHeaderFields;

public final class HeaderFields extends AbstractHeaderFields {
	
	public HeaderFields() {
		super();
	}
	
	public HeaderFields(String headersString) throws IllegalHeaderFieldNameException, IllegalHeaderFieldValueException {
		super(headersString);
	}

	public HeaderFields(HeaderField... newHeaders) throws IllegalHeaderFieldNameException, IllegalHeaderFieldValueException {
		super(newHeaders);
	}

	@Override
	public Iterator<HeaderField> iterator() {
		return Collections.unmodifiableCollection(headers.values()).iterator();
	}
}
