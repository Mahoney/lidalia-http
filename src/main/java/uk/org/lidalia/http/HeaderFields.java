package uk.org.lidalia.http;

import java.util.Iterator;

import uk.org.lidalia.http.headers.HeaderFieldType;
import uk.org.lidalia.http.headers.HeaderFieldValue;

public interface HeaderFields extends Iterable<HeaderField> {

	HeaderFieldValue get(HeaderFieldType name);

	Iterator<HeaderField> iterator();
	
	int size();
	
	boolean isEmpty();

}