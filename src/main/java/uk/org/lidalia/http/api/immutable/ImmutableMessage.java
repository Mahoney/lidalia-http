package uk.org.lidalia.http.api.immutable;

import uk.org.lidalia.http.api.Message;
import uk.org.lidalia.lang.Immutable;

public interface ImmutableMessage extends Message, Immutable {

    @Override
    ImmutableHeader getHeader();

    @Override
    ImmutableHeaderFields getHeaderFields();

    @Override
    ImmutableBody getBody();
}
