package uk.org.lidalia.http.api.response;

import org.joda.time.Seconds;

import uk.org.lidalia.http.api.Header;
import uk.org.lidalia.http.api.headerfield.HeaderFieldValue;
import uk.org.lidalia.http.api.immutable.response.ImmutableResponseHeader;
import uk.org.lidalia.http.api.mutable.response.MutableResponseHeader;

public interface ResponseHeader extends Header {

    public Code getCode();
    public Reason getReason();

    public HeaderFieldValue getAcceptRanges();
    public Seconds getAge();
    public HeaderFieldValue getEtag();
    public HeaderFieldValue getLocation();
    public MutableResponseHeader toMutable();

    @Override
    public ImmutableResponseHeader toImmutable();
}
