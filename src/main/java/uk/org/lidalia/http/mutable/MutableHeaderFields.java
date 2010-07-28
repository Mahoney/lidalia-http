package uk.org.lidalia.http.mutable;

import java.util.Iterator;

import uk.org.lidalia.http.HeaderField;
import uk.org.lidalia.http.HeaderFields;
import uk.org.lidalia.http.exception.IllegalHeaderFieldNameException;
import uk.org.lidalia.http.exception.IllegalHeaderFieldValueException;
import uk.org.lidalia.http.headers.AbstractHeaderFields;
import uk.org.lidalia.http.headers.HeaderFieldName;
import uk.org.lidalia.http.immutable.ImmutableHeaderFields;

public class MutableHeaderFields extends AbstractHeaderFields {

	public MutableHeaderFields() {
		super();
	}

	public MutableHeaderFields(String headersString) throws IllegalHeaderFieldNameException, IllegalHeaderFieldValueException {
		super(headersString);
	}

	public MutableHeaderFields(HeaderField... newHeaders) throws IllegalHeaderFieldValueException {
		super(newHeaders);
	}

	public MutableHeaderFields(HeaderFields headerFields) {
		super(headerFields);
	}

	@Override
	public Iterator<HeaderField> iterator() {
		return headers.values().iterator();
	}
	
	public void set(HeaderField header) {
		HeaderFieldName name = header.getName();
		headers.put(name, header);
	}

	public void add(HeaderField header) throws IllegalHeaderFieldValueException {
		HeaderFieldName name = header.getName();
		HeaderField existingHeader = headers.get(name);
		if (existingHeader == null) {
			headers.put(name, header);
		} else {
			headers.put(name, new HeaderField(name, name.parseValue(existingHeader.getValue() + ", " + header.getValue())));
		}
	}

	public void clear() {
		headers.clear();
	}

	public boolean remove(HeaderField header) {
		return headers.remove(header) != null;
	}

	@Override
	public ImmutableHeaderFields toImmutable() {
		return new ImmutableHeaderFields(this);
	}

	@Override
	public MutableHeaderFields toMutable() {
		return this;
	}
}
