package uk.org.lidalia.http.mutable;

import uk.org.lidalia.http.Message;

public interface MutableMessage extends Message {
	
	@Override
	MutableBody getBody();
	
	@Override
	MutableHeader getHeader();
	
	@Override
	MutableHeaderFields getHeaderFields();
	
}
