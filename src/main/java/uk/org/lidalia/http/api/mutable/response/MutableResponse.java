package uk.org.lidalia.http.api.mutable.response;

import uk.org.lidalia.http.api.immutable.response.ImmutableResponse;
import uk.org.lidalia.http.api.mutable.MutableBody;
import uk.org.lidalia.http.api.mutable.MutableMessage;
import uk.org.lidalia.http.api.response.Response;

public interface MutableResponse extends Response, MutableMessage<ImmutableResponse, MutableResponse> {

    @Override
    MutableBody getBody();

    @Override
    MutableResponseHeader getHeader();

    void setBody(MutableBody body);

}
