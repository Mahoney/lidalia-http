package uk.org.lidalia.http.response;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.CharacterCodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

import uk.org.lidalia.http.HeaderFieldType;
import uk.org.lidalia.http.Text;

public class ResponseHeaderFieldType extends HeaderFieldType<Text> {

	public static final ResponseHeaderFieldType ACCEPT_RANGES		= makeResponseHeaderFieldType("Accept-Ranges");
	public static final ResponseHeaderFieldType AGE					= makeResponseHeaderFieldType("Age");
	public static final ResponseHeaderFieldType ETAG				= makeResponseHeaderFieldType("Etag");
	public static final ResponseHeaderFieldType LOCATION			= makeResponseHeaderFieldType("Location");
	public static final ResponseHeaderFieldType PROXY_AUTHENTICATE	= makeResponseHeaderFieldType("Proxy-Authenticate");
	public static final ResponseHeaderFieldType RETRY_AFTER			= makeResponseHeaderFieldType("Retry-After");
	public static final ResponseHeaderFieldType SERVER				= makeResponseHeaderFieldType("Server");
	public static final ResponseHeaderFieldType VARY				= makeResponseHeaderFieldType("Vary");
	public static final ResponseHeaderFieldType WWW_AUTHENTICATE	= makeResponseHeaderFieldType("WWW-Authenticate");
	public static final ResponseHeaderFieldType SET_COOKIE			= makeResponseHeaderFieldType("Set-Cookie");
	
	private static ResponseHeaderFieldType makeResponseHeaderFieldType(String safeHeaderName) {
		try {
			return new ResponseHeaderFieldType(safeHeaderName);
		} catch (CharacterCodingException e) {
			throw new IllegalStateException("It should not be possible to get a character coding exception from any of the predefined ResponseHeaderFieldTypes", e);
		}
	}

	private ResponseHeaderFieldType(String name) throws CharacterCodingException {
		super(name);
	}
	
	@Override
	public Text parseValue(String headerValue) {
		try {
			return new Text(headerValue);
		} catch (CharacterCodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private static final Map<String, ResponseHeaderFieldType> values;
	static {
		Map<String, ResponseHeaderFieldType> tempValues = new HashMap<String, ResponseHeaderFieldType>();
		for (Field field : ResponseHeaderFieldType.class.getFields()) {
			if (isPublicStaticFinal(field)) {
				try {
					tempValues.put(field.getName(), (ResponseHeaderFieldType) field.get(ResponseHeaderFieldType.class));
				} catch (IllegalAccessException e) {
					throw new IllegalStateException("It should not be possible to get an IllegalAccessException on a public field", e);
				}
			}
		}
		values = Collections.unmodifiableMap(tempValues);
	}
	public static Set<ResponseHeaderFieldType> values() {
		return Collections.unmodifiableSet(new HashSet<ResponseHeaderFieldType>(values.values()));
	}
	
	public static ResponseHeaderFieldType valueOf(String headerFieldTypeName) {
		return values.get(headerFieldTypeName);
	}

	private static boolean isPublicStaticFinal(Field field) {
		return Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers());
	}
}
