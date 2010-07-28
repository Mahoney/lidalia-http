package uk.org.lidalia.http.immutable;

import java.util.Collections;
import java.util.Iterator;


import uk.org.lidalia.Immutable;
import uk.org.lidalia.http.HeaderField;
import uk.org.lidalia.http.HeaderFields;
import uk.org.lidalia.http.exception.IllegalHeaderFieldNameException;
import uk.org.lidalia.http.exception.IllegalHeaderFieldValueException;
import uk.org.lidalia.http.headers.AbstractHeaderFields;
import uk.org.lidalia.http.mutable.MutableHeaderFields;

public final class ImmutableHeaderFields extends AbstractHeaderFields implements Immutable {
	
	public ImmutableHeaderFields() {
		super();
	}
	
	public ImmutableHeaderFields(String headersString) throws IllegalHeaderFieldNameException, IllegalHeaderFieldValueException {
		super(headersString);
	}

	public ImmutableHeaderFields(HeaderField... newHeaders) throws IllegalHeaderFieldValueException {
		super(newHeaders);
	}
	
	public ImmutableHeaderFields(HeaderFields headerFields) {
		super(headerFields);
	}

	@Override
	public Iterator<HeaderField> iterator() {
		return Collections.unmodifiableCollection(headers.values()).iterator();
	}
	
	@Override
	public ImmutableHeaderFields toImmutable() {
		return this;
	}

	@Override
	public MutableHeaderFields toMutable() {
		return new MutableHeaderFields(this);
	}
}
