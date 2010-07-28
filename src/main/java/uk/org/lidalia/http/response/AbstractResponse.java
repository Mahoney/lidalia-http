package uk.org.lidalia.http.response;

import org.joda.time.Seconds;

import uk.org.lidalia.http.headers.HeaderFieldValue;

public abstract class AbstractResponse implements Response {

	@Override
	public ResponseCode getCode() {
		return getHeader().getCode();
	}

	@Override
	public Reason getReason() {
		return getHeader().getReason();
	}
	
	@Override
	public HeaderFieldValue getAcceptRanges() {
		return getHeader().getAcceptRanges();
	}

	@Override
	public Seconds getAge() {
		return getHeader().getAge();
	}

	@Override
	public HeaderFieldValue getEtag() {
		return getHeader().getEtag();
	}

	@Override
	public HeaderFieldValue getLocation() {
		return getHeader().getLocation();
	}

	@Override
	public String toString() {
		return getHeader() + "\r\n" + getBody();
	}
	
}
