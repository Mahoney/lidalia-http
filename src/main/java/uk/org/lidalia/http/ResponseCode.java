package uk.org.lidalia.http;

import org.apache.commons.lang.Validate;

public final class ResponseCode {
	
	public static final ResponseCode OK = ResponseCodeRegistry.get(200);
	
	private final int code;
	private final Reason defaultReason;
	
	ResponseCode(int code) {
		this(code, null);
	}
	
	ResponseCode(int code, Reason defaultReason) {
		Validate.isTrue(code >= 100);
		Validate.isTrue(code <= 999);
		this.code = code;
		this.defaultReason = defaultReason;
	}
	
	public int getCode() {
		return code;
	}
	
	@Override
	public String toString() {
		return Integer.toString(code);
	}

	public Reason getDefaultReason() {
		return defaultReason;
	}

}
