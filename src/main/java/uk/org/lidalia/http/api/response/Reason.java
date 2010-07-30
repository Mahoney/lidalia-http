package uk.org.lidalia.http.api.response;

import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.api.Text;


public final class Reason extends Text {

	public Reason(String reason) {
		super(reason);
		Validate.isTrue(!reason.contains("\r"), "[" + reason + "] contains a CR or LF character");
	}
}
