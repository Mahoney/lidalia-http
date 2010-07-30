package uk.org.lidalia.http.response;

import java.nio.charset.CharacterCodingException;

import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.Text;


public final class Reason extends Text {

	public Reason(String reason) throws CharacterCodingException {
		super(reason);
		Validate.isTrue(!reason.contains("\r"), "[" + reason + "] contains a CR or LF character");
	}
}
