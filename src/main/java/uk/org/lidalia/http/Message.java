package uk.org.lidalia.http;

public interface Message {

	public abstract Header getHeader();

	public abstract Body getBody();

	public abstract HeaderFields getHeaderFields();

}