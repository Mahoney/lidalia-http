package uk.org.lidalia.http.api.mutable;

import uk.org.lidalia.http.api.Message;
import uk.org.lidalia.http.api.immutable.ImmutableMessage;

public interface MutableMessage<I extends ImmutableMessage<I, M>, M extends MutableMessage<I, M>> extends Message<I, M> {

    @Override
    MutableBody getBody();

    @Override
    MutableHeader getHeader();

    @Override
    MutableHeaderFields getHeaderFields();

}
