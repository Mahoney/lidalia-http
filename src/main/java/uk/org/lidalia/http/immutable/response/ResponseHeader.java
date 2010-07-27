package uk.org.lidalia.http.immutable.response;

import java.util.regex.Matcher;

import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.exception.InvalidHeaderException;
import uk.org.lidalia.http.immutable.HeaderFields;
import uk.org.lidalia.http.response.AbstractResponseHeader;
import uk.org.lidalia.http.response.Reason;
import uk.org.lidalia.http.response.ResponseCode;
import uk.org.lidalia.http.response.ResponseCodeRegistry;

public class ResponseHeader extends AbstractResponseHeader {
	
	private final ResponseCode code;
	private final Reason reason;
	private final HeaderFields headers;
	
	public ResponseHeader(ResponseCode code) {
		this(code, null, null);
	}
	
	public ResponseHeader(ResponseCode code, Reason reason) {
		this(code, reason, null);
	}
	
	public ResponseHeader(ResponseCode code, HeaderFields headers) {
		this(code, null, headers);
	}

	public ResponseHeader(ResponseCode code, Reason reason, HeaderFields headers) {
		Validate.notNull(code);
		this.code = code;
		this.reason = reason == null ? code.getDefaultReason() : reason;
		this.headers = headers == null ? new HeaderFields() : headers;
	}
	
	public ResponseHeader(String headerString) throws InvalidHeaderException {
		try {
			Matcher headerMatcher = parseHeader(headerString);
			code = ResponseCodeRegistry.get(Integer.valueOf(headerMatcher.group(1)));
			reason = new Reason(headerMatcher.group(2));
			headers = new HeaderFields(headerMatcher.group(3));
		} catch (Exception e) {
			throw new InvalidHeaderException(headerString, e);
		}
	}

	@Override
	public ResponseCode getCode() {
		return code;
	}

	@Override
	public Reason getReason() {
		return reason;
	}
	
	@Override
	public HeaderFields getHeaderFields() {
		return headers;
	}
}
