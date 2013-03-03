package uk.org.lidalia.http.api.mutable;

import uk.org.lidalia.http.api.Header;
import uk.org.lidalia.http.api.immutable.ImmutableHeader;

public interface MutableHeader<I extends ImmutableHeader<I, M>, M extends MutableHeader<I, M>> extends Header<I, M> {

}
