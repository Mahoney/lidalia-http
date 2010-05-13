package uk.org.lidalia.http;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface HeaderFields extends Iterable<HeaderField> {

	public abstract Object fetch(HeaderFieldType name) throws NoSuchElementException;

	public abstract Object get(HeaderFieldType name);

	public abstract Iterator<HeaderField> iterator();

}