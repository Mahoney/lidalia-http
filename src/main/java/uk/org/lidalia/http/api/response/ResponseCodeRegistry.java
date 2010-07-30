package uk.org.lidalia.http.api.response;

import java.nio.charset.CharacterCodingException;
import java.util.Collection;import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class ResponseCodeRegistry {
	
	private static final ConcurrentMap<Integer, ResponseCode> responseCodes = new ConcurrentHashMap<Integer, ResponseCode>();
	
	public static Collection<ResponseCode> values() {
		return Collections.unmodifiableCollection(responseCodes.values());
	}
	
	public static ResponseCode get(Integer code) {
		ResponseCode result = responseCodes.get(code);
		if (result == null) {
			result = new ResponseCode(code);
			ResponseCode actual = responseCodes.putIfAbsent(code, result);
			if (actual != null) {
				result = actual;
			}
		}
		return result;
	}
	
	public static void register(int code, String defaultReason) throws CharacterCodingException {
		responseCodes.putIfAbsent(code, new ResponseCode(code, new Reason(defaultReason)));
	}
}
