package uk.org.lidalia.http.response;

import org.joda.time.Seconds;

import uk.org.lidalia.http.Header;
import uk.org.lidalia.http.headers.HeaderFieldValue;

public interface ResponseHeader extends Header {

	public ResponseCode getCode();
	public Reason getReason();
	
	public HeaderFieldValue getAcceptRanges();
	public Seconds getAge();
	public HeaderFieldValue getEtag();
	public HeaderFieldValue getLocation();
}
