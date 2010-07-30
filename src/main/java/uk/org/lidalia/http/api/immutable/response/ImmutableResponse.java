package uk.org.lidalia.http.api.immutable.response;

import uk.org.lidalia.http.api.immutable.ImmutableMessage;
import uk.org.lidalia.http.api.response.Response;


public interface ImmutableResponse extends Response, ImmutableMessage {
	
	@Override
	ImmutableResponseHeader getHeader();
	
	@Override
	ImmutableResponseBody getBody();
	
}
