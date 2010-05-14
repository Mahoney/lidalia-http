package uk.org.lidalia.http.response;

import uk.org.lidalia.http.Header;

public interface ResponseHeader extends Header {

	public ResponseCode getCode();
	public Reason getReason();
	
}
