package uk.org.lidalia.http.mutable.response;

import java.util.regex.Matcher;

import uk.org.lidalia.http.HeaderField;
import uk.org.lidalia.http.exception.IllegalHeaderFieldValueException;
import uk.org.lidalia.http.exception.InvalidHeaderException;
import uk.org.lidalia.http.immutable.response.ImmutableResponseHeader;
import uk.org.lidalia.http.mutable.MutableHeaderFields;
import uk.org.lidalia.http.response.AbstractResponseHeader;
import uk.org.lidalia.http.response.Reason;
import uk.org.lidalia.http.response.ResponseCode;
import uk.org.lidalia.http.response.ResponseCodeRegistry;
import uk.org.lidalia.http.response.ResponseHeader;

public class MutableResponseHeader extends AbstractResponseHeader {
	
	private ResponseCode code;
	private Reason reason;
	private final MutableHeaderFields headers;
	
	public MutableResponseHeader() {
		this(null, null, null);
	}
	
	public MutableResponseHeader(ResponseCode code) {
		this(code, null, null);
	}

	public MutableResponseHeader(ResponseCode code, Reason reason) {
		this(code, reason, null);
	}
	
	public MutableResponseHeader(ResponseCode code, MutableHeaderFields mutableHeaderFields) {
		this(code, null, mutableHeaderFields);
	}
	
	public MutableResponseHeader(ResponseCode code, Reason reason, MutableHeaderFields headers) {
		this.code = code;
		Reason defaultReason = code == null ? null : code.getDefaultReason();
		this.reason = reason == null ? defaultReason : reason;
		this.headers = headers == null ? new MutableHeaderFields() : headers;
	}
	
	public MutableResponseHeader(String headerString) throws InvalidHeaderException {
		try {
			Matcher headerMatcher = parseHeader(headerString);
			code = ResponseCodeRegistry.get(Integer.valueOf(headerMatcher.group(1)));
			reason = new Reason(headerMatcher.group(2));
			headers = new MutableHeaderFields(headerMatcher.group(3));
		} catch (Exception e) {
			throw new InvalidHeaderException(headerString, e);
		}
	}

	public MutableResponseHeader(ResponseHeader responseHeader) {
		this(responseHeader.getCode(), responseHeader.getReason(), responseHeader.getHeaderFields().toMutable());
	}

	@Override
	public ResponseCode getCode() {
		return code;
	}
	
	public void setCode(ResponseCode code) {
		this.code = code;
	}

	@Override
	public Reason getReason() {
		return reason;
	}
	
	public void setReason(Reason reason) {
		this.reason = reason;
	}

	@Override
	public MutableHeaderFields getHeaderFields() {
		return headers;
	}
	
	public void setHeaderField(HeaderField header) {
		headers.set(header);
	}

	public void addHeaderField(HeaderField header) throws IllegalHeaderFieldValueException {
		headers.add(header);
	}

	public boolean removeHeaderField(HeaderField header) {
		return headers.remove(header);
	}

	@Override
	public ImmutableResponseHeader toImmutable() {
		return new ImmutableResponseHeader(this);
	}

	@Override
	public MutableResponseHeader toMutable() {
		return this;
	}
}
