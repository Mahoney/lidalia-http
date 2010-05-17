package uk.org.lidalia.testutils;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.concurrent.Callable;

public class Assert {

	@SuppressWarnings("unchecked")
	public static <E extends Throwable> E shouldThrow(Class<E> throwableType, Callable<Void> callable) throws Throwable {
		try {
			callable.call();
			fail("No exception thrown; expected a " + throwableType.getName());
			return null;
		} catch (Throwable t) {
			if (instanceOf(t, throwableType)) {
				return (E) t;
			}
			throw t;
		}
	}
	
	public static void shouldThrow(Throwable expected, Callable<?> callable) throws Throwable {
		try {
			callable.call();
			fail("No exception thrown; should have thrown " + expected);
		} catch (Throwable actual) {
			if (instanceOf(actual, expected.getClass())) {
				assertSame(expected, actual);
				return;
			}
			throw actual;
		}
	}
	
	public static boolean instanceOf(Object o, Class<?> c) {
		return c.isAssignableFrom(o.getClass());
	}
	
	private Assert() {
		throw new UnsupportedOperationException("Not instantiable");
	}
}
