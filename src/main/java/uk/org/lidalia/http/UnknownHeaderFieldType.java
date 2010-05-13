package uk.org.lidalia.http;

public class UnknownHeaderFieldType implements HeaderFieldType {

	private String name;
	
	public UnknownHeaderFieldType(String headerName) {
		this.name = headerName;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object parseValue(String headerValue) {
		return headerValue;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}