package uk.org.lidalia.http;

import java.util.Iterator;

import uk.org.lidalia.http.headers.HeaderFieldName;
import uk.org.lidalia.http.headers.HeaderFieldValue;

public interface HeaderFields extends Iterable<HeaderField> {

	HeaderFieldValue get(HeaderFieldName name);

	Iterator<HeaderField> iterator();
	
	int size();
	
	boolean isEmpty();

}