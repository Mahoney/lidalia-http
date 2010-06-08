package uk.org.lidalia;

import org.apache.commons.lang.Validate;

public abstract class StringWrapper {
	
	private final String wrappedString;

	public StringWrapper(String wrappedString) {
		Validate.notNull(wrappedString);
		this.wrappedString = wrappedString;
	}

	@Override
	public final String toString() {
		return wrappedString;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getClass().hashCode();
		result = prime * result + wrappedString.hashCode();
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj.getClass().equals(this.getClass())))
			return false;
		StringWrapper other = (StringWrapper) obj;
		return wrappedString.equals(other.wrappedString);
	}
}
