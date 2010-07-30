package uk.org.lidalia.http.api.mutable.response;

import uk.org.lidalia.http.api.mutable.MutableMessage;
import uk.org.lidalia.http.api.response.Response;

public interface MutableResponse extends Response, MutableMessage {

	@Override
	MutableResponseBody getBody();
	
	@Override
	MutableResponseHeader getHeader();
	
	void setBody(MutableResponseBody body);
	
}
