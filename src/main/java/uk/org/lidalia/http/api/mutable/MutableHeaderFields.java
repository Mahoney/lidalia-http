package uk.org.lidalia.http.api.mutable;

import uk.org.lidalia.http.api.HeaderFields;
import uk.org.lidalia.http.api.exception.IllegalHeaderFieldValueException;
import uk.org.lidalia.http.api.headerfield.HeaderField;

public interface MutableHeaderFields extends HeaderFields {

    boolean remove(HeaderField header);

    void clear();

    void add(HeaderField header) throws IllegalHeaderFieldValueException;

    void set(HeaderField header);

}
