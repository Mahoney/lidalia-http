package uk.org.lidalia.http.api;

import uk.org.lidalia.CanBeMadeImmutable;
import uk.org.lidalia.http.api.immutable.ImmutableMessage;

public interface Message extends CanBeMadeImmutable {

	Header getHeader();

	HeaderFields getHeaderFields();
	
	Body getBody();
	
	@Override
	ImmutableMessage toImmutable();

}
