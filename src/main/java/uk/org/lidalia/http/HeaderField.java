package uk.org.lidalia.http;

import java.nio.charset.CharacterCodingException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

public class HeaderField<E> {
	
	protected final HeaderFieldType<E> name;
	protected final E value;
	
	public HeaderField(String headerString) throws CharacterCodingException {
		String headerName = StringUtils.substringBefore(headerString, ":");
		String headerValue = StringUtils.substringAfter(headerString, ":").trim();
		this.name = HeaderFieldTypeRegistry.get(headerName);
		this.value = name.parseValue(headerValue);
	}

	public HeaderField(HeaderFieldType<E> name, E value) {
		Validate.notNull(name, "HeaderName cannot be null");
		Validate.notNull(value, "HeaderValue cannot be null");
		this.name = name;
		this.value = value;
	}
	
	public HeaderFieldType<E> getName() {
		return name;
	}
	
	public Object getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return name + ":" + value;
	}
}
