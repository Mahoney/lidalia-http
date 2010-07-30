package uk.org.lidalia.http.api.headerfield;

import uk.org.lidalia.Immutable;

public interface HeaderField extends Immutable {

	HeaderFieldName getName();

	HeaderFieldValue getValue();

	HeaderField toImmutable();

}