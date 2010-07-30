package uk.org.lidalia.http.api.response;

import uk.org.lidalia.http.api.Body;
import uk.org.lidalia.http.api.immutable.response.ImmutableResponseBody;
import uk.org.lidalia.http.api.mutable.response.MutableResponseBody;

public interface ResponseBody extends Body {

	MutableResponseBody toMutable();

	@Override
	ImmutableResponseBody toImmutable();

}
