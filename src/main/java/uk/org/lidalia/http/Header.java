package uk.org.lidalia.http;

import uk.org.lidalia.CanBeMadeImmutable;
import uk.org.lidalia.http.immutable.ImmutableHeader;

public interface Header extends CanBeMadeImmutable {

	HeaderFields getHeaderFields();
	
	@Override
	ImmutableHeader toImmutable();

}
