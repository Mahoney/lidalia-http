package uk.org.lidalia.http.immutable;

import uk.org.lidalia.Immutable;
import uk.org.lidalia.http.Message;

public interface ImmutableMessage extends Message, Immutable {
	
	@Override
	ImmutableHeader getHeader();
	
	@Override
	ImmutableHeaderFields getHeaderFields();
	
	@Override
	ImmutableBody getBody();
}
