package uk.org.lidalia.http;

import java.util.HashMap;
import java.util.Map;

public enum ResponseCodes implements ResponseCode {

	
	OK(200);
	
	private static final Map<Integer, ResponseCode> responseCodes = new HashMap<Integer, ResponseCode>();
	static {
		for (ResponseCodes responseCode : ResponseCodes.values()) {
			responseCodes.put(responseCode.code, responseCode);
		}
	}
	
	private final int code;
	
	private ResponseCodes(int code) {
		this.code = code;
	}
	
	@Override
	public int getCode() {
		return code;
	}
	
	public static ResponseCode get(int code) {
		ResponseCode result = responseCodes.get(code);
		if (result == null) {
			result = new UnknownHTTPResponseCode(code);
			responseCodes.put(code, result);
		}
		return result;
	}
	
	private static class UnknownHTTPResponseCode implements ResponseCode {
		private final int code;

		private UnknownHTTPResponseCode(int code) {
			this.code = code;
		}
		
		@Override
		public int getCode() {
			return code;
		}
		
	}
}
