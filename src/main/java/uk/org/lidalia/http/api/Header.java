package uk.org.lidalia.http.api;

import uk.org.lidalia.http.api.immutable.ImmutableHeader;
import uk.org.lidalia.lang.CanBeMadeImmutable;

public interface Header extends CanBeMadeImmutable {

	HeaderFields getHeaderFields();
	
	@Override
	ImmutableHeader toImmutable();

}
