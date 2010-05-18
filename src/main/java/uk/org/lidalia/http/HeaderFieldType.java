package uk.org.lidalia.http;

import java.nio.charset.CharacterCodingException;

public interface HeaderFieldType {

	String getName();
	Object parseValue(String headerValue) throws CharacterCodingException;
}
