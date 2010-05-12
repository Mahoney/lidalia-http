package uk.org.lidalia.http;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

public abstract class HeaderField {
	
	protected final HeaderFieldType name;
	protected final HeaderFieldValue value;

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
