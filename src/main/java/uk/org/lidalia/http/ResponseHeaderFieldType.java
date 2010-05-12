package uk.org.lidalia.http;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public enum ResponseHeaderFieldType implements HeaderFieldType {

	ACCEPT_RANGES("Accept-Ranges"),
	AGE("Age"),
	ETAG("Etag"),
	LOCATION("Location"),
	PROXY_AUTHENTICATE("Proxy-Authenticate"),
	RETRY_AFTER("Retry-After"),
	SERVER("Server"),
	VARY("Vary"),
	WWW_AUTHENTICATE("WWW-Authenticate");
	
	private final String name;

	private ResponseHeaderFieldType(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public HeaderFieldValue getValue(String headerValue) {
		return null;
	}

	@Override
	public String toString() {
		return name;
	}
	
	private static final ConcurrentMap<String, HeaderFieldType> types = new ConcurrentHashMap<String, HeaderFieldType>();
	
	static {
		for (ResponseHeaderFieldType type : ResponseHeaderFieldType.values()) {
			types.put(type.name, type);
		}
	}

	public static HeaderFieldType get(String headerName) {
		HeaderFieldType type = types.get(headerName);
		if (type == null) {
			type = new HeaderFieldType.UnknownHeaderFieldType(headerName);
		}
		return null;
	}
	
	public static void registerResponseHeaderFieldType(HeaderFieldType newType) {
		types.putIfAbsent(newType.getName(), newType);
	}
}
