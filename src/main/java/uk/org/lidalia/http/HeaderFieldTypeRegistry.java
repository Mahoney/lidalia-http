package uk.org.lidalia.http;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class HeaderFieldTypeRegistry {

	private static final ConcurrentMap<String, HeaderFieldType> types = new ConcurrentHashMap<String, HeaderFieldType>();
	
	static {
		for (ResponseHeaderFieldType type : ResponseHeaderFieldType.values()) {
			types.put(type.getName(), type);
		}
	}

	public static HeaderFieldType get(String headerName) {
		HeaderFieldType type = types.get(headerName);
		if (type == null) {
			type = new UnknownHeaderFieldType(headerName);
			HeaderFieldType actual = types.putIfAbsent(headerName, type);
			if (actual != null) {
				type = actual;
			}
		}
		return type;
	}
	
	public static void registerResponseHeaderFieldType(HeaderFieldType newType) {
		types.putIfAbsent(newType.getName(), newType);
	}
}
