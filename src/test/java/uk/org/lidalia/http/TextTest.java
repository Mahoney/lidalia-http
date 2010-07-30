package uk.org.lidalia.http;

import static org.junit.Assert.assertEquals;
import static uk.org.lidalia.testutils.Assert.shouldThrow;

import java.nio.charset.Charset;
import java.util.concurrent.Callable;

import org.junit.Test;

import uk.org.lidalia.http.api.Text;

public class TextTest {
	
	@Test
	public void constructorThrowsIllegalArgumentExceptionOnControlCharacter() throws Throwable {
		final String string = new String(new byte[]{12}, Charset.forName("ISO-8859-1"));
		assertExceptionThrown(string);
	}
	
	@Test
	public void constructorAcceptsNormalText() throws Throwable {
		String string = "Hello World\t\t\t         99+\"'/\\^&*";
		Text text = new Text(string);
		assertEquals(string, text.toString());
	}
	
	@Test
	public void constructorAcceptsEmptyText() throws Throwable {
		String string = "";
		Text text = new Text(string);
		assertEquals(string, text.toString());
	}
	
	@Test
	public void constructorAcceptsCRLFWithFollowingSpace() throws Throwable {
		String string = "Hello World\r\n ";
		Text text = new Text(string);
		assertEquals(string, text.toString());
	}
	
	@Test
	public void constructorAcceptsCRLFWithFollowingHorizontalTab() throws Throwable {
		String string = "Hello World\r\n\t";
		Text text = new Text(string);
		assertEquals(string, text.toString());
	}
	
	@Test
	public void constructorRefusesCarriageReturnNotFollowedByWhitespace() throws Throwable {
		assertExceptionThrown("Hello World\r  ");
		assertExceptionThrown("Hello World\n  ");
		assertExceptionThrown("Hello World\r\n");
		assertExceptionThrown("Hello World\r\nb");
		assertExceptionThrown("Hello World\r\n\t\r");
		assertExceptionThrown("\r Hello World");
		assertExceptionThrown("\n Hello World");
		assertExceptionThrown("\r\nHello World");
		assertExceptionThrown("\r\n\t\rHello World");
	}
	
	private void assertExceptionThrown(final String string) throws Throwable {
		IllegalArgumentException exception = shouldThrow(IllegalArgumentException.class, new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				new Text(string);
				return null;
			}
		});
		assertEquals("[" + string + "] is not valid Text - contains control characters", exception.getMessage());
	}
}
