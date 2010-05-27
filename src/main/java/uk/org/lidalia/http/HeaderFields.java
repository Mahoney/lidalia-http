package uk.org.lidalia.http;

import java.util.Iterator;

public interface HeaderFields extends Iterable<HeaderField> {

	public abstract <E> E get(HeaderFieldType<E> name);

	public abstract Iterator<HeaderField> iterator();
	
	int size();
	
	boolean isEmpty();

}