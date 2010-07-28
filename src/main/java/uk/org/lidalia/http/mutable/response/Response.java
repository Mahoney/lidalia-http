package uk.org.lidalia.http.mutable.response;

import uk.org.lidalia.http.exception.InvalidResponseException;
import uk.org.lidalia.http.mutable.HeaderFields;
import uk.org.lidalia.http.response.AbstractResponse;
import uk.org.lidalia.http.response.Reason;
import uk.org.lidalia.http.response.ResponseCode;
import uk.org.lidalia.http.response.ResponseStringParser;

public class Response extends AbstractResponse {

	private final ResponseHeader header;
	private ResponseBody body;
	
	public Response(String responseString) throws InvalidResponseException {
		try {
			ResponseStringParser responseStringParser = new ResponseStringParser(responseString);
			this.header = new ResponseHeader(responseStringParser.getHeaderString());
			this.body = ResponseBody.parse(responseStringParser.getBodyString());
		} catch (Exception e) {
			throw new InvalidResponseException(responseString, e);
		}
	}
	
	public Response(ResponseHeader header, ResponseBody body) {
		this.header = header == null ? new ResponseHeader() : header;
		this.body = body;
	}
	
	public Response() {
		this(null, null, null, null);
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
	
	public void setBody(ResponseBody body) {
		this.body = body;
	}
	
	@Override
	public HeaderFields getHeaderFields() {
		return header.getHeaderFields();
	}
}
