package uk.org.lidalia.http;

import org.apache.commons.lang.Validate;

public final class ResponseCode {
	
	public static final ResponseCode OK = ResponseCodeRegistry.get(200);
	
	private final int code;
	
	ResponseCode(int code) {
		this.code = code;
		Validate.isTrue(code >= 0);
		Validate.isTrue(code < 1000);
	}
	
	public int getCode() {
		return code;
	}
	
	@Override
	public String toString() {
		return Integer.toString(code);
	}

}
