package uk.org.lidalia.http.api.mutable;

import uk.org.lidalia.http.api.Message;

public interface MutableMessage extends Message {

    @Override
    MutableBody getBody();

    @Override
    MutableHeader getHeader();

    @Override
    MutableHeaderFields getHeaderFields();

}
