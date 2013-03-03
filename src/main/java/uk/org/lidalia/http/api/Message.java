package uk.org.lidalia.http.api;

import uk.org.lidalia.http.api.immutable.ImmutableMessage;
import uk.org.lidalia.http.api.mutable.MutableMessage;
import uk.org.lidalia.lang.CanBeMadeImmutable;
import uk.org.lidalia.lang.CanBeMadeMutable;

public interface Message<I extends ImmutableMessage<I, M>, M extends MutableMessage<I, M>> extends CanBeMadeImmutable<I>, CanBeMadeMutable<M> {

    Header getHeader();

    HeaderFields getHeaderFields();

    Body getBody();
}
