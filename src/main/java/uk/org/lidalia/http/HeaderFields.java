package uk.org.lidalia.http;

import java.util.Iterator;

import uk.org.lidalia.CanBeMadeImmutable;
import uk.org.lidalia.http.headers.HeaderFieldName;
import uk.org.lidalia.http.headers.HeaderFieldValue;
import uk.org.lidalia.http.immutable.ImmutableHeaderFields;
import uk.org.lidalia.http.mutable.MutableHeaderFields;

public interface HeaderFields extends Iterable<HeaderField>, CanBeMadeImmutable {

	HeaderFieldValue get(HeaderFieldName name);

	@Override
	Iterator<HeaderField> iterator();
	
	int size();
	
	boolean isEmpty();

	@Override
	ImmutableHeaderFields toImmutable();

	MutableHeaderFields toMutable();

	HeaderField[] toArray();

}