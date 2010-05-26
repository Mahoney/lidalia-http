package uk.org.lidalia.http.immutable;

import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.HeaderFieldType;
import uk.org.lidalia.http.HeaderField;

public class HeaderFields implements uk.org.lidalia.http.HeaderFields {
	
	private final Map<HeaderFieldType, HeaderField> headers = new LinkedHashMap<HeaderFieldType, HeaderField>();
	
	public HeaderFields(String headersString) throws CharacterCodingException {
		this(parseHeaders(headersString));
	}

	private static HeaderField[] parseHeaders(String headersString) throws CharacterCodingException {
		String headersWithoutLinearWhitespace = headersString.replaceAll("\r\n( |\t)+", " ");
		String[] headerStrings = StringUtils.split(headersWithoutLinearWhitespace, "\r\n");
		List<HeaderField> headers = new ArrayList<HeaderField>();
		for (String headerString : headerStrings) {
			headers.add(new HeaderField(headerString));
		}
		return headers.toArray(new HeaderField[] {});
	}

	public HeaderFields(HeaderField... newHeaders) throws CharacterCodingException {
		for (HeaderField header : newHeaders) {
			HeaderField existingHeader = headers.get(header.getName());
			if (existingHeader == null) {
				headers.put(header.getName(), header);
			} else {
				headers.put(header.getName(), new HeaderField(header.getName() + ": " + existingHeader.getValue() + ", " + header.getValue()));
			}
		}
	}

	@Override
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
		return StringUtils.join(headers.values(), "\r\n");
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
