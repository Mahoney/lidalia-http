package uk.org.lidalia.http;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.exception.IllegalHeaderNameException;
import uk.org.lidalia.http.exception.IllegalHeaderValueException;

public class HeaderField {
	
	protected final HeaderFieldType name;
	protected final HeaderFieldValue value;
	
	public HeaderField(String headerString) throws IllegalHeaderNameException, IllegalHeaderValueException {
		String headerName = StringUtils.substringBefore(headerString, ":");
		String headerValue = StringUtils.substringAfter(headerString, ":").trim();
		this.name = HeaderFieldTypeRegistry.get(headerName);
		this.value = name.parseValue(headerValue);
	}

	public HeaderField(HeaderFieldType name, HeaderFieldValue value) {
		Validate.notNull(name, "HeaderName cannot be null");
		Validate.notNull(value, "HeaderValue cannot be null");
		this.name = name;
		this.value = value;
	}
	
	public HeaderFieldType getName() {
		return name;
	}
	
	public HeaderFieldValue getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return name + ":" + value;
	}
}
