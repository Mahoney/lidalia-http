package uk.org.lidalia.http;

import uk.org.lidalia.CanBeMadeImmutable;
import uk.org.lidalia.http.immutable.ImmutableBody;

public interface Body extends CanBeMadeImmutable {

	ImmutableBody toImmutable();
}
