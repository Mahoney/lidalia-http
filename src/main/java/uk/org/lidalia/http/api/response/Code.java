package uk.org.lidalia.http.api.response;

import uk.org.lidalia.Immutable;

public interface Code extends Immutable {

	int getCode();

	Reason getDefaultReason();

	Code toImmutable();

	String toString();
}