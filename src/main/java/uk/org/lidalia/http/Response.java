package uk.org.lidalia.http;

public interface Response extends Message {

	@Override
	public ResponseHeader getHeader();
	
	@Override
	public ResponseBody getBody();
}
