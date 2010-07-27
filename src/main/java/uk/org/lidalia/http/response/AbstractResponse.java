package uk.org.lidalia.http.response;

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
	public String toString() {
		return getHeader() + "\r\n" + getBody();
	}
	
}
