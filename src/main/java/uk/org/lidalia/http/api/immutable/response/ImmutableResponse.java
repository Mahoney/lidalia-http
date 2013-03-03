package uk.org.lidalia.http.api.immutable.response;

import uk.org.lidalia.http.api.immutable.ImmutableBody;
import uk.org.lidalia.http.api.immutable.ImmutableMessage;
import uk.org.lidalia.http.api.mutable.response.MutableResponse;
import uk.org.lidalia.http.api.response.Response;

public interface ImmutableResponse extends Response, ImmutableMessage<ImmutableResponse, MutableResponse> {

    @Override
    ImmutableResponseHeader getHeader();

    @Override
    ImmutableBody getBody();

}
