package uk.org.lidalia.http.api.response;

import org.apache.commons.lang3.Validate;

import uk.org.lidalia.http.api.Text;

public final class Reason extends Text {

    public static Reason Reason(String reason) {
        return new Reason(reason);
    }

    private Reason(String reason) {
        super(reason);
        Validate.isTrue(!reason.contains("\r"), "[" + reason + "] contains a CR or LF character");
    }
}
