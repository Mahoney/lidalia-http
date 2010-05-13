package uk.org.lidalia.http;

public enum ResponseHeaderFieldType implements HeaderFieldType {

	ACCEPT_RANGES("Accept-Ranges"),
	AGE("Age"),
	ETAG("Etag"),
	LOCATION("Location"),
	PROXY_AUTHENTICATE("Proxy-Authenticate"),
	RETRY_AFTER("Retry-After"),
	SERVER("Server"),
	VARY("Vary"),
	WWW_AUTHENTICATE("WWW-Authenticate"),
	SET_COOKIE("Set-Cookie");
	
	private final String name;

	private ResponseHeaderFieldType(String name) {
		this.name = name;
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
