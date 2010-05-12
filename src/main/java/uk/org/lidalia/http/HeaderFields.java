package uk.org.lidalia.http;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface HeaderFields extends Iterable<HeaderField> {

	public abstract HeaderFieldValue fetch(HeaderFieldType name) throws NoSuchElementException;

	public abstract HeaderFieldValue get(HeaderFieldType name);

	public abstract Iterator<HeaderField> iterator();

}