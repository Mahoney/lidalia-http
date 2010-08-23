package uk.org.lidalia.http.response;

import static org.junit.Assert.*;
import static uk.org.lidalia.testutils.Assert.shouldThrow;
import static uk.org.lidalia.http.api.response.Reason.Reason;

import java.util.concurrent.Callable;

import org.junit.Test;

public class ReasonTest {

	@Test
	public void stringConstructorDoesNotAllowCROrLF() throws Throwable {
		assertExceptionThrown("glah\r\n blah");
	}
	
	@Test
	public void testSimpleTrue() {
		String s = "humbapumpa jim";
		assertTrue(s.matches(".*(jim|joe).*"));
		s = "humbapumpa jom";
		assertFalse(s.matches(".*(jim|joe).*"));
		s = "humbaPumpa joe";
		assertTrue(s.matches(".*(jim|joe).*"));
		s = "humbapumpa joejim";
		assertTrue(s.matches(".*(j|o).*"));
	}

	
	private void assertExceptionThrown(final String string) throws Throwable {
		IllegalArgumentException exception = shouldThrow(IllegalArgumentException.class, new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				Reason(string);
				return null;
			}
		});
		assertEquals("[" + string + "] contains a CR or LF character", exception.getMessage());
	}
}
