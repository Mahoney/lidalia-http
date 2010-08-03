package uk.org.lidalia.http.api.immutable;

import uk.org.lidalia.http.api.Header;
import uk.org.lidalia.lang.Immutable;

public interface ImmutableHeader extends Header, Immutable {
	
	@Override
	ImmutableHeaderFields getHeaderFields();
}
