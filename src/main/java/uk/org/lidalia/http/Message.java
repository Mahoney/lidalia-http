package uk.org.lidalia.http;

import uk.org.lidalia.MakeImmutable;

public interface Message extends MakeImmutable {

	public abstract Header getHeader();

	public abstract Body getBody();

	public abstract HeaderFields getHeaderFields();

}