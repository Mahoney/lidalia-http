package uk.org.lidalia.http.headers;

import uk.org.lidalia.Immutable;
import uk.org.lidalia.WrappedValue;

public abstract class AbstractHeaderFieldValue<E> extends WrappedValue<E> implements HeaderFieldValue {

	public AbstractHeaderFieldValue(E wrappedValue) {
		super(wrappedValue);
	}
	
	@Override
	public final String toString() {
		return toText().toString();
	}

	@Override
	public Immutable toImmutable() {
		return this;
	}
}
