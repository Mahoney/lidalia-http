package uk.org.lidalia.http.api.headerfield;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import uk.org.lidalia.http.api.Token;
import uk.org.lidalia.http.api.exception.IllegalTokenException;
import uk.org.lidalia.lang.MapUtils;

public class HeaderFieldName extends Token {
	
	private static final ConcurrentMap<String, WeakReference<HeaderFieldName>> names = new ConcurrentHashMap<String, WeakReference<HeaderFieldName>>();
	
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

	public static HeaderFieldName HeaderFieldName(String name) {
		WeakReference<HeaderFieldName> ref = names.get(name);
		HeaderFieldName result = null;
		if (ref != null) {
			result = ref.get();
			if (result == null) {
				result = MapUtils.putIfAbsentReturningValue(names, name, new WeakReference<HeaderFieldName>(new HeaderFieldName(name))).get();
			}
		}
		return result;
	}
}
