package uk.org.lidalia.http.mutable;

import uk.org.lidalia.http.HeaderFields;
import uk.org.lidalia.http.exception.IllegalHeaderFieldValueException;
import uk.org.lidalia.http.headerfield.HeaderField;

public interface MutableHeaderFields extends HeaderFields {

	boolean remove(HeaderField header);

	void clear();

	void add(HeaderField header) throws IllegalHeaderFieldValueException;

	void set(HeaderField header);

}
