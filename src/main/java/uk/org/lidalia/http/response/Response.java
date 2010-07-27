package uk.org.lidalia.http.response;

import uk.org.lidalia.http.Message;

public interface Response extends Message {

	@Override
	public ResponseHeader getHeader();
	
	@Override
	public ResponseBody getBody();

	public abstract ResponseCode getCode();

	public abstract Reason getReason();
}
