package uk.org.lidalia.http.headers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.HeaderField;
import uk.org.lidalia.http.HeaderFields;
import uk.org.lidalia.http.exception.IllegalHeaderFieldNameException;
import uk.org.lidalia.http.exception.IllegalHeaderFieldValueException;

public abstract class AbstractHeaderFields implements HeaderFields {

	protected final Map<HeaderFieldName, HeaderField> headers = new LinkedHashMap<HeaderFieldName, HeaderField>();

	private static HeaderField[] parseHeaders(String headersString) throws IllegalHeaderFieldNameException, IllegalHeaderFieldValueException {
		String headersWithoutLinearWhitespace = headersString.replaceAll("\r\n( |\t)+", " ");
		String[] headerStrings = StringUtils.split(headersWithoutLinearWhitespace, "\r\n");
		List<HeaderField> headers = new ArrayList<HeaderField>();
		for (String headerString : headerStrings) {
			headers.add(new HeaderField(headerString));
		}
		return headers.toArray(new HeaderField[] {});
	}
	protected AbstractHeaderFields() {
		super();
	}
	
	protected AbstractHeaderFields(String headersString) throws IllegalHeaderFieldNameException, IllegalHeaderFieldValueException {
		this(parseHeaders(headersString));
	}

	protected AbstractHeaderFields(HeaderField... newHeaders) throws IllegalHeaderFieldNameException, IllegalHeaderFieldValueException {
		for (HeaderField header : newHeaders) {
			HeaderFieldName name = header.getName();
			HeaderField existingHeader = headers.get(name);
			if (existingHeader == null) {
				headers.put(name, header);
			} else {
				headers.put(name, new HeaderField(name, name.parseValue(existingHeader.getValue() + ", " + header.getValue())));
			}
		}
	}

	@Override
	public HeaderFieldValue get(HeaderFieldName headerFieldName) {
		Validate.notNull(headerFieldName, "headerFieldName is null");
		HeaderField header = headers.get(headerFieldName);
		return header != null ? header.getValue() : null;
	}

	public boolean contains(HeaderFieldName name) {
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