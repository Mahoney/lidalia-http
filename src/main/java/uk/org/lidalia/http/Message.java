package uk.org.lidalia.http;

import uk.org.lidalia.CanBeMadeImmutable;

public interface Message extends CanBeMadeImmutable {

	public abstract Header getHeader();

	public abstract Body getBody();

	public abstract HeaderFields getHeaderFields();

}