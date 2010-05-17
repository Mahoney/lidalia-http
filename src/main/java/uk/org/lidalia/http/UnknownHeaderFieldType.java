package uk.org.lidalia.http;

public class UnknownHeaderFieldType extends Token implements HeaderFieldType {

	public UnknownHeaderFieldType(String headerName) {
		super(headerName);
	}

	@Override
	public String getName() {
		return toString();
	}

	@Override
	public Object parseValue(String headerValue) {
		return new Text(headerValue);
	}
}
