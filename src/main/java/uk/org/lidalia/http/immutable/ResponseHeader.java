package uk.org.lidalia.http.immutable;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.ResponseCode;
import uk.org.lidalia.http.ResponseCodeRegistry;
import uk.org.lidalia.http.exception.InvalidHeaderException;

public class ResponseHeader implements uk.org.lidalia.http.ResponseHeader {
	
	private final ResponseCode code;
	private final String reason;
	private final HeaderFields headers;
	
	public ResponseHeader(String headerString) throws InvalidHeaderException {
		try {
			String status = StringUtils.substringBefore(headerString, "\r\n");
			List<String> statusElements = Arrays.asList(status.split(" "));
			Validate.isTrue(statusElements.get(0).equals("HTTP/1.1"));
			code = ResponseCodeRegistry.get(Integer.valueOf(statusElements.get(1)));
			reason = StringUtils.join(statusElements.subList(2, statusElements.size()), " ");
//			Validate.isTrue(reason.matches(""));
			
			String headersString = StringUtils.substringAfter(headerString, "\r\n");
			System.out.println(headersString.getClass() + " " + headersString);
			headers = new HeaderFields(headersString);
		} catch (Exception e) {
			throw new InvalidHeaderException(headerString, e);
		}
	}
	
	public ResponseHeader(ResponseCode code, String reason, HeaderFields headers) {
		this.code = code;
		this.reason = reason;
		this.headers = headers;
	}

	@Override
	public ResponseCode getCode() {
		return code;
	}

	@Override
	public String getReason() {
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
