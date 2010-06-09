package uk.org.lidalia.http.headers;

import java.nio.charset.CharacterCodingException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import uk.org.lidalia.http.exception.IllegalHeaderNameException;
import uk.org.lidalia.http.response.ResponseHeaderFieldType;

public class HeaderFieldTypeRegistry {

	private static final ConcurrentMap<String, HeaderFieldType> types = new ConcurrentHashMap<String, HeaderFieldType>();
	
	static {
		for (ResponseHeaderFieldType type : ResponseHeaderFieldType.values()) {
			types.put(type.toString(), type);
		}
	}

	public static HeaderFieldType get(String headerName) throws IllegalHeaderNameException {
		HeaderFieldType type = types.get(headerName);
		if (type == null) {
			try {
				type = new UnknownHeaderFieldType(headerName);
			} catch (CharacterCodingException e) {
				throw new IllegalHeaderNameException(headerName + " is not a legal header name", e);
			}
			HeaderFieldType actual = types.putIfAbsent(headerName, type);
			if (actual != null) {
				type = actual;
			}
		}
		return type;
	}
	
	public static void registerResponseHeaderFieldType(HeaderFieldType newType) {
		types.putIfAbsent(newType.toString(), newType);
	}
}
