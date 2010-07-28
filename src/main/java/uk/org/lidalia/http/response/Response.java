package uk.org.lidalia.http.response;

import org.joda.time.Seconds;

import uk.org.lidalia.http.Message;
import uk.org.lidalia.http.headers.HeaderFieldValue;

public interface Response extends Message {

	@Override
	ResponseHeader getHeader();
	
	@Override
	ResponseBody getBody();

	ResponseCode getCode();

	Reason getReason();
	
	HeaderFieldValue getAcceptRanges();
	Seconds getAge();
	HeaderFieldValue getEtag();
	HeaderFieldValue getLocation();
}
