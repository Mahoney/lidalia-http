package uk.org.lidalia.http;

import java.nio.charset.CharacterCodingException;

import org.apache.commons.lang.Validate;
import org.joda.time.Seconds;

import uk.org.lidalia.http.exception.IllegalHeaderValueException;

public final class PositiveSeconds extends HeaderFieldValue {

	private final Seconds seconds;
	
	public PositiveSeconds(Seconds seconds) throws IllegalHeaderValueException {
		try {
			Validate.notNull(seconds);
			Validate.isTrue(seconds.getSeconds() >= 0);
			this.seconds = seconds;
		} catch (Exception exception) {
			throw new IllegalHeaderValueException(seconds + " is not a positive not-null value", exception);
		}
	}
	
	public Seconds getSeconds() {
		return seconds;
	}
	
	@Override
	public Text toText() {
		try {
			return new Text(Integer.toString(seconds.getSeconds()));
		} catch (CharacterCodingException e) {
			throw new IllegalStateException("It should be impossible for [" + seconds.getSeconds() + "] to throw this exception", e);
		}
	}

	@Override
	public int hashCode() {
		return 31 * seconds.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PositiveSeconds))
			return false;
		PositiveSeconds other = (PositiveSeconds) obj;
		return seconds.equals(other.seconds);
	}
	

}
