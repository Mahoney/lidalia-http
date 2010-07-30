package uk.org.lidalia.http.api;

import uk.org.lidalia.CanBeMadeImmutable;
import uk.org.lidalia.http.api.immutable.ImmutableBody;

public interface Body extends CanBeMadeImmutable {

	ImmutableBody toImmutable();
}
