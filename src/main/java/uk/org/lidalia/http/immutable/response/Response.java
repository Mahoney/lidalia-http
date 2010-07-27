package uk.org.lidalia.http.immutable.response;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.exception.InvalidResponseException;
import uk.org.lidalia.http.immutable.HeaderFields;
import uk.org.lidalia.http.response.AbstractResponse;
import uk.org.lidalia.http.response.Reason;
import uk.org.lidalia.http.response.ResponseCode;

public class Response extends AbstractResponse {

	private final ResponseHeader header;
	private final ResponseBody body;
	
	public Response(String responseString) throws InvalidResponseException {
		try {
			Validate.isTrue(responseString.contains("\r\n\r\n"), "A Response must have a double CLRF after the header");
			String headerString = StringUtils.substringBefore(responseString, "\r\n\r\n");
			String bodyString = StringUtils.substringAfter(responseString, "\r\n\r\n");
			this.header = new ResponseHeader(headerString);
			this.body = ResponseBody.parse(bodyString);
		} catch (Exception e) {
			throw new InvalidResponseException(responseString, e);
		}
	}
	
	public Response(ResponseHeader header, ResponseBody body) {
		Validate.notNull(header, "header is null");
		this.header = header;
		this.body = body;
	}
	
	public Response(ResponseCode code) {
		this(code, null, null, null);
	}
	
	public Response(ResponseCode code, Reason reason) {
		this(code, reason, null, null);
	}
	
	public Response(ResponseCode code, HeaderFields headers) {
		this(code, null, headers, null);
	}

	public Response(ResponseCode code, Reason reason, HeaderFields headers) {
		this(code, reason, headers, null);
	}
	
	public Response(ResponseCode code, ResponseBody body) {
		this(code, null, null, body);
	}
	
	public Response(ResponseCode code, Reason reason, ResponseBody body) {
		this(code, reason, null, body);
	}
	
	public Response(ResponseCode code, HeaderFields headers, ResponseBody body) {
		this(code, null, headers, body);
	}

	public Response(ResponseCode code, Reason reason, HeaderFields headers, ResponseBody body) {
		this(new ResponseHeader(code, reason, headers), body);
	}

	@Override
	public ResponseHeader getHeader() {
		return header;
	}
	
	@Override
	public ResponseBody getBody() {
		return body;
	}
	
	@Override
	public HeaderFields getHeaderFields() {
		return header.getHeaderFields();
	}
}
