package uk.org.lidalia.http.response;

import org.apache.commons.lang.Validate;

import uk.org.lidalia.Immutable;

public final class ResponseCode implements Immutable {
	
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
	
	@Override
	public ResponseCode toImmutable() {
		return this;
	}

}
