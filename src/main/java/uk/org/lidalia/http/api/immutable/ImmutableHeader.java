package uk.org.lidalia.http.api.immutable;

import uk.org.lidalia.http.api.Header;
import uk.org.lidalia.http.api.mutable.MutableHeader;
import uk.org.lidalia.lang.Immutable;

public interface ImmutableHeader<I extends ImmutableHeader<I, M>, M extends MutableHeader<I, M>> extends Header<I, M>, Immutable<I> {

    @Override
    ImmutableHeaderFields getHeaderFields();
}
