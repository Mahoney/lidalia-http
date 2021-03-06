package uk.org.lidalia.http.api.response;

import org.joda.time.Seconds;

import uk.org.lidalia.http.api.Message;
import uk.org.lidalia.http.api.headerfield.HeaderFieldValue;
import uk.org.lidalia.http.api.immutable.response.ImmutableResponse;
import uk.org.lidalia.http.api.mutable.response.MutableResponse;

public interface Response extends Message<ImmutableResponse, MutableResponse> {

    @Override
    ResponseHeader getHeader();

    Code getCode();

    Reason getReason();

    HeaderFieldValue getAcceptRanges();
    Seconds getAge();
    HeaderFieldValue getEtag();
    HeaderFieldValue getLocation();
}
