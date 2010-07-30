package uk.org.lidalia.http.api.mutable.response;

import uk.org.lidalia.http.api.exception.IllegalHeaderFieldValueException;
import uk.org.lidalia.http.api.headerfield.HeaderField;
import uk.org.lidalia.http.api.mutable.MutableHeader;
import uk.org.lidalia.http.api.mutable.MutableHeaderFields;
import uk.org.lidalia.http.api.response.Code;
import uk.org.lidalia.http.api.response.Reason;
import uk.org.lidalia.http.api.response.ResponseHeader;

public interface MutableResponseHeader extends ResponseHeader, MutableHeader {

	@Override
	public MutableHeaderFields getHeaderFields();

	void setCode(Code code);

	void setReason(Reason reason);

	void setHeaderField(HeaderField header);

	void addHeaderField(HeaderField header) throws IllegalHeaderFieldValueException;

	boolean removeHeaderField(HeaderField header);
}
