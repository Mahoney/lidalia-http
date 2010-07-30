package uk.org.lidalia.http.immutable.response;

import uk.org.lidalia.http.immutable.ImmutableMessage;
import uk.org.lidalia.http.response.Response;


public interface ImmutableResponse extends Response, ImmutableMessage {
	
	@Override
	ImmutableResponseHeader getHeader();
	
	@Override
	ImmutableResponseBody getBody();
	
}
