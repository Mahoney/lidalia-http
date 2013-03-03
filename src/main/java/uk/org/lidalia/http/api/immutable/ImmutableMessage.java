package uk.org.lidalia.http.api.immutable;

import uk.org.lidalia.http.api.Message;
import uk.org.lidalia.http.api.mutable.MutableMessage;
import uk.org.lidalia.lang.Immutable;

public interface ImmutableMessage<I extends ImmutableMessage<I, M>, M extends MutableMessage<I, M>> extends Message<I, M>, Immutable<I> {

    @Override
    ImmutableHeader getHeader();

    @Override
    ImmutableHeaderFields getHeaderFields();

    @Override
    ImmutableBody getBody();
}
