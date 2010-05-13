package uk.org.lidalia.http;

public interface HeaderFieldType {

	String getName();
	Object parseValue(String headerValue);
}
