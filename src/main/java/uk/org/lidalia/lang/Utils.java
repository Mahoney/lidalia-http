package uk.org.lidalia.lang;

import java.util.concurrent.ConcurrentMap;

public final class Utils {

	public static <E> E valueOrDefault(E valueWhichMightBeNull, E defaultValue) {
		if (valueWhichMightBeNull == null) {
			return defaultValue;
		} else {
			return valueWhichMightBeNull;
		}
	}
	
	public static String throwableToString(String baseToString, Throwable cause) {
		StringBuffer stringValue = new StringBuffer(baseToString);
		if (cause != null) {
			stringValue.append("; caused by: ").append(cause);
		}
		return stringValue.toString();
	}
	
	private Utils() {
		// not instantiable
	}

	public static <K, V> V putIfAbsentReturningObjectInMap(ConcurrentMap<K, V> map, K key, V mayNotBeInMap) {
		V alreadyInMap = map.putIfAbsent(key, mayNotBeInMap);
		if (alreadyInMap != null) {
			return alreadyInMap;
		} else {
			return mayNotBeInMap;
		}
	}
	
	public static byte[] copy(byte[] original) {
		byte[] newBytes = new byte[original.length];
		System.arraycopy(original, 0, newBytes, 0, original.length);
		return newBytes;
	}
}
