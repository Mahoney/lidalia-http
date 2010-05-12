package uk.org.lidalia.http;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

public class MessageHeader {
	
	private final HeaderType name;
	private final HeaderValue value;
	
	public MessageHeader(String headerString) {
		String headerName = StringUtils.substringBefore(headerString, ":");
		String headerValue = StringUtils.substringAfter(headerString, ":");
		this.name = HeaderTypes.get(headerName);
		this.value = name.getValue(headerValue);
	}

	public MessageHeader(HeaderType name, HeaderValue value) {
		Validate.notNull(name, "HeaderName cannot be null");
		Validate.notNull(value, "HeaderValue cannot be null");
		this.name = name;
		this.value = value;
	}
	
	public HeaderType getName() {
		return name;
	}
	
	public HeaderValue getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return name + ":" + value;
	}
}
