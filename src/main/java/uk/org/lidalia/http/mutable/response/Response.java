package uk.org.lidalia.http.mutable.response;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.exception.InvalidResponseException;

public class Response implements uk.org.lidalia.http.response.Response {

	private final ResponseHeader header;
	private ResponseBody body;
	
	public Response() {
		this.header = new ResponseHeader();
	}
	
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
	
	public Response(ResponseHeader header, ResponseBody body) throws InvalidResponseException {
		Validate.notNull(header, "header is null");
		this.header = header;
		this.body = body;
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
	public String toString() {
		return header + "\r\n" + body;
	}

}
