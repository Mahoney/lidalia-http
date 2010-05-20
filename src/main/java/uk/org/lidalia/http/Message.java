package uk.org.lidalia.http;

public interface Message {

	public abstract Headers getHeaders();

	public abstract Body getBody();

}