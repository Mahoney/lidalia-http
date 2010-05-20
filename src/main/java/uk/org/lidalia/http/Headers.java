package uk.org.lidalia.http;

import java.util.Iterator;

public interface Headers extends Iterable<HeaderField> {

	public abstract Object get(HeaderFieldType name);

	public abstract Iterator<HeaderField> iterator();
	
	int size();
	
	boolean isEmpty();

}