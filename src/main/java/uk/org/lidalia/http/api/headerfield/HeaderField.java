package uk.org.lidalia.http.api.headerfield;

import uk.org.lidalia.lang.Immutable;

public interface HeaderField<T extends HeaderField<T>> extends Immutable<T> {

    HeaderFieldName getName();

    HeaderFieldValue getValue();

    @Override
    T toImmutable();

}
