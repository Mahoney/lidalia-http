package uk.org.lidalia.http.api;

import uk.org.lidalia.CanBeMadeImmutable;
import uk.org.lidalia.http.api.immutable.ImmutableHeader;

public interface Header extends CanBeMadeImmutable {

	HeaderFields getHeaderFields();
	
	@Override
	ImmutableHeader toImmutable();

}
