package uk.org.lidalia.http.immutable.response;

import uk.org.lidalia.http.immutable.ImmutableHeader;
import uk.org.lidalia.http.immutable.ImmutableHeaderFields;
import uk.org.lidalia.http.response.ResponseHeader;


public interface ImmutableResponseHeader extends ResponseHeader, ImmutableHeader {

	@Override
	public ImmutableHeaderFields getHeaderFields();
}
