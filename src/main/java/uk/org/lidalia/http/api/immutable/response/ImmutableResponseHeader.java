package uk.org.lidalia.http.api.immutable.response;

import uk.org.lidalia.http.api.immutable.ImmutableHeader;
import uk.org.lidalia.http.api.immutable.ImmutableHeaderFields;
import uk.org.lidalia.http.api.response.ResponseHeader;


public interface ImmutableResponseHeader extends ResponseHeader, ImmutableHeader {

	@Override
	public ImmutableHeaderFields getHeaderFields();
}
