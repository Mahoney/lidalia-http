package uk.org.lidalia.http.api.mutable.request;

import uk.org.lidalia.http.api.immutable.request.ImmutableRequest;
import uk.org.lidalia.http.api.mutable.MutableMessage;
import uk.org.lidalia.http.api.request.Request;

public interface MutableRequest extends Request, MutableMessage<ImmutableRequest, MutableRequest> {
}
