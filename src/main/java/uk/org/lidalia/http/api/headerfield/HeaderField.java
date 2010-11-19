package uk.org.lidalia.http.api.headerfield;

import uk.org.lidalia.lang.Immutable;

public interface HeaderField extends Immutable {

	HeaderFieldName getName();

	HeaderFieldValue getValue();

	@Override
	HeaderField toImmutable();

}
