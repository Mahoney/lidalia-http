package uk.org.lidalia.http.api.request;

import uk.org.lidalia.http.api.Message;
import uk.org.lidalia.http.api.immutable.request.ImmutableRequest;
import uk.org.lidalia.http.api.mutable.request.MutableRequest;


public interface Request extends Message<ImmutableRequest, MutableRequest> {

}
