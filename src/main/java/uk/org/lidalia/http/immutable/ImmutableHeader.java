package uk.org.lidalia.http.immutable;

import uk.org.lidalia.Immutable;
import uk.org.lidalia.http.Header;

public interface ImmutableHeader extends Header, Immutable {
	
	@Override
	ImmutableHeaderFields getHeaderFields();
}
