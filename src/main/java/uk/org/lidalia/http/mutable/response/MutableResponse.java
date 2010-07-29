package uk.org.lidalia.http.mutable.response;

import uk.org.lidalia.Utils;
import uk.org.lidalia.http.exception.InvalidResponseException;
import uk.org.lidalia.http.immutable.response.ImmutableResponse;
import uk.org.lidalia.http.mutable.MutableHeaderFields;
import uk.org.lidalia.http.response.AbstractResponse;
import uk.org.lidalia.http.response.Reason;
import uk.org.lidalia.http.response.Response;
import uk.org.lidalia.http.response.ResponseCode;
import uk.org.lidalia.http.response.ResponseStringParser;

public class MutableResponse extends AbstractResponse {

	private final MutableResponseHeader header;
	private MutableResponseBody body;
	
	public MutableResponse(String responseString) throws InvalidResponseException {
		try {
			ResponseStringParser responseStringParser = new ResponseStringParser(responseString);
			this.header = new MutableResponseHeader(responseStringParser.getHeaderString());
			this.body = MutableResponseBody.parse(responseStringParser.getBodyString());
		} catch (Exception e) {
			throw new InvalidResponseException(responseString, e);
		}
	}
	
	public MutableResponse() {
		this(null, null, null, null);
	}
	
	public MutableResponse(MutableResponseHeader header) {
		this(header, null);
	}
	
	public MutableResponse(MutableResponseHeader header, MutableResponseBody body) {
		this.header = Utils.thisOrDefault(header, new MutableResponseHeader());
		this.body = body;
	}
	
	public MutableResponse(ResponseCode code) {
		this(code, null, null, null);
	}
	
	public MutableResponse(ResponseCode code, Reason reason) {
		this(code, reason, null, null);
	}
	
	public MutableResponse(ResponseCode code, MutableHeaderFields headers) {
		this(code, null, headers, null);
	}

	public MutableResponse(ResponseCode code, Reason reason, MutableHeaderFields headers) {
		this(code, reason, headers, null);
	}
	
	public MutableResponse(ResponseCode code, MutableResponseBody body) {
		this(code, null, null, body);
	}
	
	public MutableResponse(ResponseCode code, Reason reason, MutableResponseBody body) {
		this(code, reason, null, body);
	}
	
	public MutableResponse(ResponseCode code, MutableHeaderFields headers, MutableResponseBody body) {
		this(code, null, headers, body);
	}

	public MutableResponse(ResponseCode code, Reason reason, MutableHeaderFields headers, MutableResponseBody body) {
		this(new MutableResponseHeader(code, reason, headers), body);
	}

	public MutableResponse(Response response) {
		this(response.getHeader().toMutable(), response.getBody().toMutable());
	}

	@Override
	public MutableResponseHeader getHeader() {
		return header;
	}

	@Override
	public MutableResponseBody getBody() {
		return body;
	}
	
	public void setBody(MutableResponseBody body) {
		this.body = body;
	}
	
	@Override
	public MutableHeaderFields getHeaderFields() {
		return header.getHeaderFields();
	}

	@Override
	public ImmutableResponse toImmutable() {
		return new ImmutableResponse(this);
	}

	@Override
	public MutableResponse toMutable() {
		return this;
	}
}
