package uk.org.lidalia.http.api.immutable;

import uk.org.lidalia.Immutable;
import uk.org.lidalia.http.api.Header;

public interface ImmutableHeader extends Header, Immutable {
	
	@Override
	ImmutableHeaderFields getHeaderFields();
}
