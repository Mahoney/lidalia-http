package uk.org.lidalia.http.mutable.response;

import uk.org.lidalia.http.exception.IllegalHeaderFieldValueException;
import uk.org.lidalia.http.headerfield.HeaderField;
import uk.org.lidalia.http.mutable.MutableHeader;
import uk.org.lidalia.http.mutable.MutableHeaderFields;
import uk.org.lidalia.http.response.Reason;
import uk.org.lidalia.http.response.ResponseCode;
import uk.org.lidalia.http.response.ResponseHeader;

public interface MutableResponseHeader extends ResponseHeader, MutableHeader {

	@Override
	public MutableHeaderFields getHeaderFields();

	void setCode(ResponseCode code);

	void setReason(Reason reason);

	void setHeaderField(HeaderField header);

	void addHeaderField(HeaderField header) throws IllegalHeaderFieldValueException;

	boolean removeHeaderField(HeaderField header);
}
