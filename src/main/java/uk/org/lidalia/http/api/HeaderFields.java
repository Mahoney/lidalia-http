package uk.org.lidalia.http.api;

import java.util.Iterator;

import uk.org.lidalia.http.api.headerfield.HeaderFieldName;
import uk.org.lidalia.http.api.headerfield.HeaderFieldValue;
import uk.org.lidalia.http.api.headerfield.HeaderField;
import uk.org.lidalia.http.api.immutable.ImmutableHeaderFields;
import uk.org.lidalia.http.api.mutable.MutableHeaderFields;
import uk.org.lidalia.lang.CanBeMadeImmutable;

public interface HeaderFields extends Iterable<HeaderField>, CanBeMadeImmutable {

	HeaderFieldValue get(HeaderFieldName name);

	@Override
	Iterator<HeaderField> iterator();
	
	int size();
	
	boolean isEmpty();

	@Override
	ImmutableHeaderFields toImmutable();

	MutableHeaderFields toMutable();

}