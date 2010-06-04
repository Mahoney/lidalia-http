package uk.org.lidalia.http;

public abstract class HeaderFieldValue {

	public abstract Text toText();
	
	@Override
	public String toString() {
		return toText().toString();
	}
}
