package uk.org.lidalia.http.immutable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.Validate;
import org.joda.time.Seconds;

import uk.org.lidalia.http.HeaderFieldValue;
import uk.org.lidalia.http.PositiveSeconds;
import uk.org.lidalia.http.exception.InvalidHeaderException;
import uk.org.lidalia.http.response.Reason;
import uk.org.lidalia.http.response.ResponseCode;
import uk.org.lidalia.http.response.ResponseCodeRegistry;
import uk.org.lidalia.http.response.ResponseHeaderFieldType;

public class ResponseHeader implements uk.org.lidalia.http.response.ResponseHeader {
	
	private static final String ANYTHING = "(?:.|\u0085|\r|\n)*";
	private static final String CODE = "(\\d\\d\\d)";
	private static final String REASON = "((?:.|\u0085)*)";
	private static final String CRLF = "\\r\\n";
	private static final String OPTIONAL_CRLF = "(?:" + CRLF + ")?";
	private static final String HEADERS = "(" + ANYTHING + ")";
	private static final String HEADER_REGEX = "^HTTP/1.1 " + CODE + " " + REASON + OPTIONAL_CRLF + HEADERS + "$";

	private static final Pattern STATUS_LINE_PATTERN = Pattern.compile(HEADER_REGEX);
	
	private final ResponseCode code;
	private final Reason reason;
	private final HeaderFields headers;
	
	
	public ResponseHeader(String headerString) throws InvalidHeaderException {
		try {
			Matcher headerMatcher = STATUS_LINE_PATTERN.matcher(headerString);
			Validate.isTrue(headerMatcher.matches(), "[" + headerString + "] must match " + HEADER_REGEX);
			code = ResponseCodeRegistry.get(Integer.valueOf(headerMatcher.group(1)));
			reason = new Reason(headerMatcher.group(2));
			headers = new HeaderFields(headerMatcher.group(3));
		} catch (Exception e) {
			throw new InvalidHeaderException(headerString, e);
		}
	}
	
	public ResponseHeader(ResponseCode code, Reason reason, HeaderFields headers) {
		Validate.notNull(code);
		Validate.notNull(reason);
		Validate.notNull(headers);
		this.code = code;
		this.reason = reason;
		this.headers = headers;
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
	
	@Override
	public String toString() {
		return "HTTP/1.1 " + code + " " + reason + "\r\n" + headers;
	}

	@Override
	public HeaderFieldValue getAcceptRanges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seconds getAge() {
		PositiveSeconds positiveSeconds = (PositiveSeconds) headers.get(ResponseHeaderFieldType.AGE);
		return positiveSeconds != null ? positiveSeconds.getSeconds() : null;
	}

	@Override
	public HeaderFieldValue getEtag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HeaderFieldValue getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

}
