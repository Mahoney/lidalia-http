package uk.org.lidalia.http.immutable;

import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.HeaderFieldType;
import uk.org.lidalia.http.HeaderField;

public class Headers implements uk.org.lidalia.http.Headers {
	
	private final Map<HeaderFieldType, HeaderField> headers;
	
	public Headers(String headersString) throws CharacterCodingException {
		this(parseHeaders(headersString));
	}

	private static HeaderField[] parseHeaders(String headersString) throws CharacterCodingException {
		String[] headerStrings = StringUtils.split(headersString, "\r\n");
		List<HeaderField> headers = new ArrayList<HeaderField>();
		for (String headerString : headerStrings) {
			headers.add(new HeaderField(headerString));
		}
		return (HeaderField[]) headers.toArray(new HeaderField[] {});
	}

	public Headers(HeaderField... newHeaders) {
		Map<HeaderFieldType, HeaderField> headers = new LinkedHashMap<HeaderFieldType, HeaderField>();
		for (HeaderField header : newHeaders) {
			headers.put(header.getName(), header);
		}
		this.headers = Collections.unmodifiableMap(headers);
	}

	public Object get(HeaderFieldType name) {
		Validate.notNull(name, "HeaderFieldType cannot be null");
		HeaderField header = headers.get(name);
		return header != null ? header.getValue() : null;
	}

	public boolean contains(HeaderFieldType name) {
		return headers.containsKey(name);
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

	@Override
	public boolean isEmpty() {
		return headers.isEmpty();
	}

	@Override
	public int size() {
		return headers.size();
	}
}
