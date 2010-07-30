package uk.org.lidalia.http.mutable.response;

import uk.org.lidalia.http.mutable.MutableMessage;
import uk.org.lidalia.http.response.Response;

public interface MutableResponse extends Response, MutableMessage {

	@Override
	MutableResponseBody getBody();
	
	@Override
	MutableResponseHeader getHeader();
	
	void setBody(MutableResponseBody body);
	
}
