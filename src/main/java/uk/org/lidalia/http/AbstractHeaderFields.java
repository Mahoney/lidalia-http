package uk.org.lidalia.http;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.exception.IllegalHeaderNameException;
import uk.org.lidalia.http.exception.IllegalHeaderValueException;
import uk.org.lidalia.http.headers.HeaderFieldType;
import uk.org.lidalia.http.headers.HeaderFieldValue;

public abstract class AbstractHeaderFields implements HeaderFields {

	protected final Map<HeaderFieldType, HeaderField> headers = new LinkedHashMap<HeaderFieldType, HeaderField>();

	private static HeaderField[] parseHeaders(String headersString) throws IllegalHeaderNameException, IllegalHeaderValueException {
		String headersWithoutLinearWhitespace = headersString.replaceAll("\r\n( |\t)+", " ");
		String[] headerStrings = StringUtils.split(headersWithoutLinearWhitespace, "\r\n");
		List<HeaderField> headers = new ArrayList<HeaderField>();
		for (String headerString : headerStrings) {
			headers.add(new HeaderField(headerString));
		}
		return headers.toArray(new HeaderField[] {});
	}
	
	protected AbstractHeaderFields(String headersString) throws IllegalHeaderNameException, IllegalHeaderValueException {
		this(parseHeaders(headersString));
	}

	protected AbstractHeaderFields(HeaderField... newHeaders) throws IllegalHeaderNameException, IllegalHeaderValueException {
		for (HeaderField header : newHeaders) {
			HeaderFieldType headerType = header.getName();
			HeaderField existingHeader = headers.get(headerType);
			if (existingHeader == null) {
				headers.put(headerType, header);
			} else {
				headers.put(headerType, new HeaderField(headerType, headerType.parseValue(existingHeader.getValue() + ", " + header.getValue())));
			}
		}
	}

	@Override
	public HeaderFieldValue get(HeaderFieldType name) {
		Validate.notNull(name, "HeaderFieldType cannot be null");
		HeaderField header = headers.get(name);
		return header != null ? header.getValue() : null;
	}

	public boolean contains(HeaderFieldType name) {
		return headers.containsKey(name);
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