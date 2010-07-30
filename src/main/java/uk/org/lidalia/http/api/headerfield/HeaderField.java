package uk.org.lidalia.http.api.headerfield;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import uk.org.lidalia.Immutable;
import uk.org.lidalia.http.api.exception.IllegalHeaderFieldNameException;
import uk.org.lidalia.http.api.exception.IllegalHeaderFieldValueException;

public final class HeaderField implements Immutable {
	
	private final HeaderFieldName name;
	private final HeaderFieldValue value;
	
	public HeaderField(String headerString) throws IllegalHeaderFieldNameException, IllegalHeaderFieldValueException {
		String headerName = StringUtils.substringBefore(headerString, ":");
		String headerValue = StringUtils.substringAfter(headerString, ":").trim();
		this.name = HeaderFieldNameRegistry.get(headerName);
		this.value = name.parseValue(headerValue);
	}

	public HeaderField(HeaderFieldName name, HeaderFieldValue value) {
		Validate.notNull(name, "HeaderName cannot be null");
		Validate.notNull(value, "HeaderValue cannot be null");
		this.name = name;
		this.value = value;
	}
	
	public HeaderFieldName getName() {
		return name;
	}
	
	public HeaderFieldValue getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return name + ": " + value;
	}
	
	@Override
	public HeaderField toImmutable() {
		return this;
	}
}