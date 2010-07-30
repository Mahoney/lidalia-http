package uk.org.lidalia.http.api.immutable;

import uk.org.lidalia.Immutable;
import uk.org.lidalia.http.api.Message;

public interface ImmutableMessage extends Message, Immutable {
	
	@Override
	ImmutableHeader getHeader();
	
	@Override
	ImmutableHeaderFields getHeaderFields();
	
	@Override
	ImmutableBody getBody();
}
