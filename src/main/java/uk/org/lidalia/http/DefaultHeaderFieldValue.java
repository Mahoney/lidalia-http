package uk.org.lidalia.http;


public final class DefaultHeaderFieldValue extends HeaderFieldValue {
	
	private final Text value;
	
	public DefaultHeaderFieldValue(Text value) {
		this.value = value;
	}

	@Override
	public Text toText() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getClass().hashCode();
		result = prime * result + value.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof DefaultHeaderFieldValue))
			return false;
		DefaultHeaderFieldValue other = (DefaultHeaderFieldValue) obj;
		return value.equals(other.value);
	}
}
