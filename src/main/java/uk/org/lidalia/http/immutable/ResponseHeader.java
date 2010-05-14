package uk.org.lidalia.http.immutable;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.exception.InvalidHeaderException;
import uk.org.lidalia.http.response.Reason;
import uk.org.lidalia.http.response.ResponseCode;
import uk.org.lidalia.http.response.ResponseCodeRegistry;

public class ResponseHeader implements uk.org.lidalia.http.response.ResponseHeader {
	
	private final ResponseCode code;
	private final Reason reason;
	private final HeaderFields headers;
	
	public ResponseHeader(String headerString) throws InvalidHeaderException {
		try {
			Validate.isTrue(headerString.endsWith("\r\n"), "Header String should end with a CRLF");
			String status = StringUtils.substringBefore(headerString, "\r\n");
			List<String> statusElements = Arrays.asList(status.split(" "));
			Validate.isTrue(statusElements.size() >= 3 || (statusElements.size() == 2 && status.endsWith(" ")), "Status line must contain at least two spaces");
			Validate.isTrue(statusElements.get(0).equals("HTTP/1.1"), "Header must start with HTTP/1.1");
			code = ResponseCodeRegistry.get(Integer.valueOf(statusElements.get(1)));
			String reasonString = StringUtils.join(statusElements.subList(2, statusElements.size()), " ");
			reason = new Reason(reasonString);
			
			String headersString = StringUtils.substringAfter(headerString, "\r\n");
			headers = new HeaderFields(headersString);
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

}
