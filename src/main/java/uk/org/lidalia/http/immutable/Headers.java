package uk.org.lidalia.http.immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.HeaderType;
import uk.org.lidalia.http.HeaderValue;
import uk.org.lidalia.http.MessageHeader;

public class Headers implements uk.org.lidalia.http.Headers {
	
	private final Map<HeaderType, MessageHeader> headers = Collections.unmodifiableMap(new LinkedHashMap<HeaderType, MessageHeader>());
	
	public Headers(String headersString) {
		this(parseHeaders(headersString));
	}

	private static MessageHeader[] parseHeaders(String headersString) {
		String[] headerStrings = headersString.split("\r\n");
		List<MessageHeader> headers = new ArrayList<MessageHeader>();
		for (String headerString : headerStrings) {
			headers.add(new MessageHeader(headerString));
		}
		return (MessageHeader[]) headers.toArray();
	}

	public Headers(MessageHeader... newHeaders) {
		for (MessageHeader header : newHeaders) {
			headers.put(header.getName(), header);
		}
	}

	/* (non-Javadoc)
	 * @see uk.org.lidalia.http.IHeaders#fetch(uk.org.lidalia.http.HeaderName)
	 */
	public HeaderValue fetch(HeaderType name) throws NoSuchElementException {
		HeaderValue value = get(name);
		if (value == null) {
			throw new NoSuchElementException("No header with name " + name + " in " + headers.values());
		}
		return value;
	}

	/* (non-Javadoc)
	 * @see uk.org.lidalia.http.IHeaders#get(uk.org.lidalia.http.HeaderName)
	 */
	public HeaderValue get(HeaderType name) {
		Validate.notNull(name, "HeaderName cannot be null");
		MessageHeader header = headers.get(name);
		return header != null ? header.getValue() : null;
	}

	/* (non-Javadoc)
	 * @see uk.org.lidalia.http.IHeaders#iterator()
	 */
	@Override
	public Iterator<MessageHeader> iterator() {
		return headers.values().iterator();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (MessageHeader header : this) {
			stringBuilder.append(header).append("\r\n");
		}
		return stringBuilder.toString();
	}
}
