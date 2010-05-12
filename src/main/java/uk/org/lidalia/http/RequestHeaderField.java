package uk.org.lidalia.http;

import org.apache.commons.lang.Validate;

public class RequestHeaderField extends HeaderField {

	public RequestHeaderField(HeaderFieldType name, HeaderFieldValue value) {
		super(name, value);
		Validate.isTrue(!(name instanceof ResponseHeaderFieldType));
	}

}
