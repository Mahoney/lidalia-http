package uk.org.lidalia.testutils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.util.concurrent.Callable;

//import org.powermock.reflect.Whitebox;

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
	
//	public static void assertNotInstantiable(final Class<?> classThatShouldNotBeInstantiable) throws Throwable {
//		assertOnlyHasNoArgsConstructor(classThatShouldNotBeInstantiable);
//		
//		UnsupportedOperationException oue = shouldThrow(UnsupportedOperationException.class, new Callable<Void>() {
//			public Void call() throws Exception {
//				Whitebox.invokeConstructor(classThatShouldNotBeInstantiable);
//				return null;
//			}
//		});
//		assertEquals("Not instantiable", oue.getMessage());
//	}

	private static void assertOnlyHasNoArgsConstructor(final Class<?> classThatShouldNotBeInstantiable) {
		assertEquals(Object.class, classThatShouldNotBeInstantiable.getSuperclass());
		assertEquals(1, classThatShouldNotBeInstantiable.getDeclaredConstructors().length);
		final Constructor<?> constructor = classThatShouldNotBeInstantiable.getDeclaredConstructors()[0];
		assertEquals(0, constructor.getParameterTypes().length);
	}
}
