package uk.org.lidalia.http.response;

import uk.org.lidalia.http.Body;
import uk.org.lidalia.http.immutable.response.ImmutableResponseBody;
import uk.org.lidalia.http.mutable.response.MutableResponseBody;

public interface ResponseBody extends Body {

	MutableResponseBody toMutable();

	@Override
	ImmutableResponseBody toImmutable();

}
