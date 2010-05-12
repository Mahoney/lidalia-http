package uk.org.lidalia.http.immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.HeaderFieldType;
import uk.org.lidalia.http.HeaderFieldValue;
import uk.org.lidalia.http.HeaderField;

public class HeaderFields implements uk.org.lidalia.http.HeaderFields {
	
	private final Map<HeaderFieldType, HeaderField> headers = Collections.unmodifiableMap(new LinkedHashMap<HeaderFieldType, HeaderField>());
	
	public HeaderFields(String headersString) {
		this(parseHeaders(headersString));
	}

	private static HeaderField[] parseHeaders(String headersString) {
		String[] headerStrings = headersString.split("\r\n");
		List<HeaderField> headers = new ArrayList<HeaderField>();
		for (String headerString : headerStrings) {
//			headers.add(new HeaderField(headerString));
		}
		return (HeaderField[]) headers.toArray();
	}

	public HeaderFields(HeaderField... newHeaders) {
		for (HeaderField header : newHeaders) {
			headers.put(header.getName(), header);
		}
	}

	public HeaderFieldValue fetch(HeaderFieldType name) throws NoSuchElementException {
		HeaderFieldValue value = get(name);
		if (value == null) {
			throw new NoSuchElementException("No header with name " + name + " in " + headers.values());
		}
		return value;
	}

	public HeaderFieldValue get(HeaderFieldType name) {
		Validate.notNull(name, "HeaderName cannot be null");
		HeaderField header = headers.get(name);
		return header != null ? header.getValue() : null;
	}

	@Override
	public Iterator<HeaderField> iterator() {
		return headers.values().iterator();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (HeaderField header : this) {
			stringBuilder.append(header).append("\r\n");
		}
		return stringBuilder.toString();
	}
}
