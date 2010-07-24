package uk.org.lidalia.http.mutable.response;

import java.util.regex.Matcher;

import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.HeaderField;
import uk.org.lidalia.http.exception.IllegalHeaderFieldValueException;
import uk.org.lidalia.http.exception.InvalidHeaderException;
import uk.org.lidalia.http.mutable.HeaderFields;
import uk.org.lidalia.http.response.AbstractResponseHeader;
import uk.org.lidalia.http.response.Reason;
import uk.org.lidalia.http.response.ResponseCode;
import uk.org.lidalia.http.response.ResponseCodeRegistry;

public class ResponseHeader extends AbstractResponseHeader {
	
	private ResponseCode code;
	private Reason reason;
	private final HeaderFields headers;
	
	public ResponseHeader() {
		this((ResponseCode) null);
	}
	
	public ResponseHeader(ResponseCode code) {
		this(code, null);
	}

	public ResponseHeader(ResponseCode code, Reason reason) {
		this(code, reason, new HeaderFields());
	}
	
	public ResponseHeader(ResponseCode code, Reason reason, HeaderFields headerFields) {
		Validate.notNull(headerFields, "headerFields is null");
		this.code = code;
		this.reason = reason;
		this.headers = headerFields;
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
	public HeaderFields getHeaderFields() {
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
}
