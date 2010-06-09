package uk.org.lidalia.http.mutable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import uk.org.lidalia.http.AbstractHeaderFields;
import uk.org.lidalia.http.HeaderField;
import uk.org.lidalia.http.exception.IllegalHeaderNameException;
import uk.org.lidalia.http.exception.IllegalHeaderValueException;
import uk.org.lidalia.http.headers.HeaderFieldType;

public class HeaderFields extends AbstractHeaderFields {

	public HeaderFields(HeaderField[] newHeaders) throws IllegalHeaderNameException, IllegalHeaderValueException {
		super(newHeaders);
	}

	@Override
	public Iterator<HeaderField> iterator() {
		return headers.values().iterator();
	}

	public void add(HeaderField header) {
		HeaderFieldType headerType = header.getName();
		HeaderField existingHeader = headers.get(headerType);
		if (existingHeader == null) {
			headers.put(headerType, header);
		} else {
			headers.put(headerType, new HeaderField(headerType, headerType.parseValue(existingHeader.getValue() + ", " + header.getValue())));
		}
		headers.put(header.getName(), header);
	}

	public void addAll(Set<HeaderField> headers) {
		for (HeaderField headerField : headers) {
			add(headerField);
		}
	}

	public void clear() {
		headers.clear();
	}

	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
}
