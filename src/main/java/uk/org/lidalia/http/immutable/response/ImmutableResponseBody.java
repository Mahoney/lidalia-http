package uk.org.lidalia.http.immutable.response;

import uk.org.lidalia.http.mutable.response.MutableResponseBody;
import uk.org.lidalia.http.response.ResponseBody;

public class ImmutableResponseBody implements ResponseBody {
	
	private final String body;

	private ImmutableResponseBody(String body) {
		this.body = body;
	}

	public static ImmutableResponseBody parse(String bodyString) {
		return bodyString == null ? null : new ImmutableResponseBody(bodyString);
	}

	public String getBody() {
		return body;
	}
	
	@Override
	public String toString() {
		return body;
	}

	@Override
	public ImmutableResponseBody toImmutable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MutableResponseBody toMutable() {
		// TODO Auto-generated method stub
		return null;
	}
}
