package uk.org.lidalia.http.headerfield;

import uk.org.lidalia.http.Text;

public final class DefaultHeaderFieldValue extends AbstractHeaderFieldValue<Text> {
	
	public DefaultHeaderFieldValue(Text value) {
		super(value);
	}

	@Override
	public Text toText() {
		return wrappedValue;
	}
}
