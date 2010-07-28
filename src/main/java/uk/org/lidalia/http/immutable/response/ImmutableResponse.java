package uk.org.lidalia.http.immutable.response;

import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.exception.InvalidResponseException;
import uk.org.lidalia.http.immutable.ImmutableHeaderFields;
import uk.org.lidalia.http.mutable.response.MutableResponse;
import uk.org.lidalia.http.response.AbstractResponse;
import uk.org.lidalia.http.response.Reason;
import uk.org.lidalia.http.response.Response;
import uk.org.lidalia.http.response.ResponseCode;
import uk.org.lidalia.http.response.ResponseStringParser;

public class ImmutableResponse extends AbstractResponse {

	private final ImmutableResponseHeader header;
	private final ImmutableResponseBody body;
	
	public ImmutableResponse(String responseString) throws InvalidResponseException {
		try {
			ResponseStringParser responseStringParser = new ResponseStringParser(responseString);
			this.header = new ImmutableResponseHeader(responseStringParser.getHeaderString());
			this.body = ImmutableResponseBody.parse(responseStringParser.getBodyString());
		} catch (Exception e) {
			throw new InvalidResponseException(responseString, e);
		}
	}
	
	public ImmutableResponse(ImmutableResponseHeader header) {
		this(header, null);
	}
	
	public ImmutableResponse(ImmutableResponseHeader header, ImmutableResponseBody body) {
		Validate.notNull(header, "header is null");
		this.header = header;
		this.body = body;
	}
	
	public ImmutableResponse(ResponseCode code) {
		this(code, null, null, null);
	}
	
	public ImmutableResponse(ResponseCode code, Reason reason) {
		this(code, reason, null, null);
	}
	
	public ImmutableResponse(ResponseCode code, ImmutableHeaderFields headers) {
		this(code, null, headers, null);
	}

	public ImmutableResponse(ResponseCode code, Reason reason, ImmutableHeaderFields headers) {
		this(code, reason, headers, null);
	}
	
	public ImmutableResponse(ResponseCode code, ImmutableResponseBody body) {
		this(code, null, null, body);
	}
	
	public ImmutableResponse(ResponseCode code, Reason reason, ImmutableResponseBody body) {
		this(code, reason, null, body);
	}
	
	public ImmutableResponse(ResponseCode code, ImmutableHeaderFields headers, ImmutableResponseBody body) {
		this(code, null, headers, body);
	}

	public ImmutableResponse(ResponseCode code, Reason reason, ImmutableHeaderFields headers, ImmutableResponseBody body) {
		this(new ImmutableResponseHeader(code, reason, headers), body);
	}

	public ImmutableResponse(Response response) {
		this(response.getHeader().toImmutable(), response.getBody().toImmutable());
	}

	@Override
	public ImmutableResponseHeader getHeader() {
		return header;
	}
	
	@Override
	public ImmutableResponseBody getBody() {
		return body;
	}
	
	@Override
	public ImmutableHeaderFields getHeaderFields() {
		return header.getHeaderFields();
	}

	@Override
	public ImmutableResponse toImmutable() {
		return this;
	}

	@Override
	public MutableResponse toMutable() {
		return new MutableResponse(this);
	}
	
}
