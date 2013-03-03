package uk.org.lidalia.http.api.immutable.request;

import uk.org.lidalia.http.api.immutable.ImmutableMessage;
import uk.org.lidalia.http.api.mutable.request.MutableRequest;
import uk.org.lidalia.http.api.request.Request;
import uk.org.lidalia.lang.Immutable;

public interface ImmutableRequest extends Request, ImmutableMessage<ImmutableRequest, MutableRequest> {
}
