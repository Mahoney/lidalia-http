package uk.org.lidalia.http.api.headerfield;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import uk.org.lidalia.http.api.Token;
import uk.org.lidalia.http.api.exception.IllegalTokenException;
import uk.org.lidalia.lang.Utils;

public class HeaderFieldName extends Token {
	
	private static final ConcurrentMap<String, HeaderFieldName> names = new ConcurrentHashMap<String, HeaderFieldName>();
	
	public static final HeaderFieldName	ACCEPT_RANGES		= HeaderFieldName("Accept-Ranges");
	public static final HeaderFieldName AGE					= HeaderFieldName("Age");
	public static final HeaderFieldName	ETAG				= HeaderFieldName("Etag");
	public static final HeaderFieldName	LOCATION			= HeaderFieldName("Location");
	public static final HeaderFieldName	PROXY_AUTHENTICATE	= HeaderFieldName("Proxy-Authenticate");
	public static final HeaderFieldName	RETRY_AFTER			= HeaderFieldName("Retry-After");
	public static final HeaderFieldName	SERVER				= HeaderFieldName("Server");
	public static final HeaderFieldName	VARY				= HeaderFieldName("Vary");
	public static final HeaderFieldName	WWW_AUTHENTICATE	= HeaderFieldName("WWW-Authenticate");
	public static final HeaderFieldName	SET_COOKIE			= HeaderFieldName("Set-Cookie");
	
	private HeaderFieldName(String headerName) throws IllegalTokenException {
		super(headerName);
	}
	
	public static Collection<HeaderFieldName> values() {
		return Collections.unmodifiableCollection(names.values());
	}

	public static HeaderFieldName HeaderFieldName(String name) {
		HeaderFieldName result = names.get(name);
		if (result == null) {
			result = Utils.putIfAbsentReturningObjectInMap(names, name, new HeaderFieldName(name));
		}
		return result;
	}
}
