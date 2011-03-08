package uk.org.lidalia.http;

import static org.junit.Assert.assertEquals;
import static uk.org.lidalia.test.Assert.shouldThrow;

import static uk.org.lidalia.http.api.Text.Text;

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
	public void constructorAcceptsNormalText() {
		String string = "Hello World\t\t\t         99+\"'/\\^&*";
		Text text = Text(string);
		assertEquals(string, text.toString());
	}
	
	@Test
	public void constructorAcceptsEmptyText() {
		String string = "";
		Text text = Text(string);
		assertEquals(string, text.toString());
	}
	
	@Test
	public void constructorAcceptsCRLFWithFollowingSpace() {
		String string = "Hello World\r\n ";
		Text text = Text(string);
		assertEquals(string, text.toString());
	}
	
	@Test
	public void constructorAcceptsCRLFWithFollowingHorizontalTab() {
		String string = "Hello World\r\n\t";
		Text text = Text(string);
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
				Text(string);
				return null;
			}
		});
		assertEquals("[" + string + "] is not valid Text - contains control characters", exception.getMessage());
	}
}
