package uk.org.lidalia.http.headers;

import uk.org.lidalia.http.Text;

public abstract class HeaderFieldValue {

	public abstract Text toText();
	
	@Override
	public String toString() {
		return toText().toString();
	}
}
