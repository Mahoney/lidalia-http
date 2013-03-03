package uk.org.lidalia.http.api.immutable.response;

import uk.org.lidalia.http.api.immutable.ImmutableHeader;
import uk.org.lidalia.http.api.immutable.ImmutableHeaderFields;
import uk.org.lidalia.http.api.mutable.response.MutableResponseHeader;
import uk.org.lidalia.http.api.response.ResponseHeader;

public interface ImmutableResponseHeader extends ResponseHeader, ImmutableHeader<ImmutableResponseHeader, MutableResponseHeader> {

    @Override
    public ImmutableHeaderFields getHeaderFields();
}
