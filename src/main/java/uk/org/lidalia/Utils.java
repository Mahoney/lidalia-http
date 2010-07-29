package uk.org.lidalia;

public final class Utils {

	public static <E> E valueOrDefault(E valueWhichMightBeNull, E defaultValue) {
		if (valueWhichMightBeNull == null) {
			return defaultValue;
		} else {
			return valueWhichMightBeNull;
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
