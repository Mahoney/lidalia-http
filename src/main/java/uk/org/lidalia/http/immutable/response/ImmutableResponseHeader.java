package uk.org.lidalia.http.immutable.response;

import java.util.regex.Matcher;

import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.exception.InvalidHeaderException;
import uk.org.lidalia.http.immutable.ImmutableHeaderFields;
import uk.org.lidalia.http.mutable.response.MutableResponseHeader;
import uk.org.lidalia.http.response.AbstractResponseHeader;
import uk.org.lidalia.http.response.Reason;
import uk.org.lidalia.http.response.ResponseCode;
import uk.org.lidalia.http.response.ResponseCodeRegistry;
import uk.org.lidalia.http.response.ResponseHeader;

public class ImmutableResponseHeader extends AbstractResponseHeader {
	
	private final ResponseCode code;
	private final Reason reason;
	private final ImmutableHeaderFields headers;
	
	public ImmutableResponseHeader(ResponseCode code) {
		this(code, null, null);
	}
	
	public ImmutableResponseHeader(ResponseCode code, Reason reason) {
		this(code, reason, null);
	}
	
	public ImmutableResponseHeader(ResponseCode code, ImmutableHeaderFields headers) {
		this(code, null, headers);
	}

	public ImmutableResponseHeader(ResponseCode code, Reason reason, ImmutableHeaderFields headers) {
		Validate.notNull(code);
		this.code = code;
		this.reason = reason == null ? code.getDefaultReason() : reason;
		this.headers = headers == null ? new ImmutableHeaderFields() : headers;
	}
	
	public ImmutableResponseHeader(String headerString) throws InvalidHeaderException {
		try {
			Matcher headerMatcher = parseHeader(headerString);
			code = ResponseCodeRegistry.get(Integer.valueOf(headerMatcher.group(1)));
			reason = new Reason(headerMatcher.group(2));
			headers = new ImmutableHeaderFields(headerMatcher.group(3));
		} catch (Exception e) {
			throw new InvalidHeaderException(headerString, e);
		}
	}
	
	public ImmutableResponseHeader(ResponseHeader responseHeader) {
		this(responseHeader.getCode(), responseHeader.getReason(), responseHeader.getHeaderFields().toImmutable());
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
	public ImmutableHeaderFields getHeaderFields() {
		return headers;
	}

	public MutableResponseHeader toMutable() {
		return new MutableResponseHeader(this);
	}

	@Override
	public ImmutableResponseHeader toImmutable() {
		return this;
	}
}
