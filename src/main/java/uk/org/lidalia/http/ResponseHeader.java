package uk.org.lidalia.http;

public interface ResponseHeader extends Header {

	public ResponseCode getCode();
	public String getReason();
	
}
