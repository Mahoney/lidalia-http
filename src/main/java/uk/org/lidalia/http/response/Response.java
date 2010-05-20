package uk.org.lidalia.http.response;

import uk.org.lidalia.http.Message;

public interface Response extends Message {
	
	@Override
	public ResponseBody getBody();
}
