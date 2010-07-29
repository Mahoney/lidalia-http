package uk.org.lidalia;

public final class Utils {

	public static <E> E thisOrDefault(E mightBeNull, E defaultValue) {
		if (mightBeNull == null) {
			return defaultValue;
		} else {
			return mightBeNull;
		}
	}
	
	public static String throwableToString(Throwable throwable) {
		StringBuffer stringValue = new StringBuffer(throwable.toString());
		if (throwable.getCause() != null) {
			stringValue.append("; caused by: ").append(throwable.getCause());
		}
		return stringValue.toString();
	}
	
	private Utils() {
		
	}
}
