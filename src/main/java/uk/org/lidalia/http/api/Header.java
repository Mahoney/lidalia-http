package uk.org.lidalia.http.api;

import uk.org.lidalia.http.api.immutable.ImmutableHeader;
import uk.org.lidalia.http.api.mutable.MutableHeader;
import uk.org.lidalia.lang.CanBeMadeImmutable;
import uk.org.lidalia.lang.CanBeMadeMutable;

public interface Header<I extends ImmutableHeader<I, M>, M extends MutableHeader<I, M>> extends CanBeMadeImmutable<I>, CanBeMadeMutable<M> {

    HeaderFields getHeaderFields();

}
