package uk.org.lidalia.http.api;

import uk.org.lidalia.http.api.immutable.ImmutableMessage;
import uk.org.lidalia.lang.CanBeMadeImmutable;

public interface Message extends CanBeMadeImmutable {

    Header getHeader();

    HeaderFields getHeaderFields();

    Body getBody();

    @Override
    ImmutableMessage toImmutable();

}
