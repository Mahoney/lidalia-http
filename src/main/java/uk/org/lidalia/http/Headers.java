package uk.org.lidalia.http;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface Headers extends Iterable<MessageHeader> {

	public abstract HeaderValue fetch(HeaderType name) throws NoSuchElementException;

	public abstract HeaderValue get(HeaderType name);

	public abstract Iterator<MessageHeader> iterator();

}