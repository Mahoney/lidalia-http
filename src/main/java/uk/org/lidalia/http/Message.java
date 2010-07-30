package uk.org.lidalia.http;

import uk.org.lidalia.CanBeMadeImmutable;
import uk.org.lidalia.http.immutable.ImmutableMessage;

public interface Message extends CanBeMadeImmutable {

	Header getHeader();

	HeaderFields getHeaderFields();
	
	Body getBody();
	
	@Override
	ImmutableMessage toImmutable();

}
