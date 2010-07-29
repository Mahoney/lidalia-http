package uk.org.lidalia;

public abstract class WrappedString extends WrappedValue<String> implements Immutable {

	public WrappedString(String wrappedValue) {
		super(wrappedValue);
	}
	
	@Override
	public final String toString() {
		return super.toString();
	}
	
	@Override
	public WrappedString toImmutable() {
		return this;
	}

}
