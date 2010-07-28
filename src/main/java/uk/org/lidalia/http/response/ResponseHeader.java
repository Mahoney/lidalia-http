package uk.org.lidalia.http.response;

import org.joda.time.Seconds;

import uk.org.lidalia.http.Header;
import uk.org.lidalia.http.headers.HeaderFieldValue;
import uk.org.lidalia.http.immutable.response.ImmutableResponseHeader;
import uk.org.lidalia.http.mutable.response.MutableResponseHeader;

public interface ResponseHeader extends Header {

	public ResponseCode getCode();
	public Reason getReason();
	
	public HeaderFieldValue getAcceptRanges();
	public Seconds getAge();
	public HeaderFieldValue getEtag();
	public HeaderFieldValue getLocation();
	public MutableResponseHeader toMutable();
	
	@Override
	public ImmutableResponseHeader toImmutable();
}
