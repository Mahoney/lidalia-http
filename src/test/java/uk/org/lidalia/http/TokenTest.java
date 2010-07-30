package uk.org.lidalia.http;

import static org.junit.Assert.assertEquals;
import static uk.org.lidalia.testutils.Assert.shouldThrow;

import java.nio.charset.Charset;
import java.util.concurrent.Callable;

import org.junit.Test;

import uk.org.lidalia.http.api.Token;
import uk.org.lidalia.http.api.exception.IllegalTokenException;


public class TokenTest {
	
	@Test
	public void tokenCannotHaveNonUsAsciiCharacters() throws Throwable {
		for (byte i = -128; i < 0; i++) {
			final String constructorArgument = new String(new byte[]{i}, Charset.forName("ISO-8859-1"));
			assertExpectedIllegalArgumentExceptionThrown(constructorArgument);
		}
	}
	
	@Test
	public void tokenCannotHaveControlCharacters() throws Throwable {
		for (byte i = 0; i <= 31; i++) {
			final String constructorArgument = new String(new byte[]{i}, Charset.forName("ISO-8859-1"));
			assertExpectedIllegalArgumentExceptionThrown(constructorArgument);
		}
		final String constructorArgument = new String(new byte[]{127}, Charset.forName("ISO-8859-1"));
		assertExpectedIllegalArgumentExceptionThrown(constructorArgument);
	}

	@Test
	public void tokenCannotBeEmpty() throws Throwable {
		assertExpectedIllegalArgumentExceptionThrown("");
	}
	
	private static final byte[] SEPARATORS = {
		'(', ')', '<', '>',  '@',
		',', ';', ':', '\\', '"',
		'/', '[', ']', '?',  '=',
		'{', '}', ' '
		};
	
	@Test
	public void tokenCannotHaveSeparators() throws Throwable {
		for (int i = 0; i < SEPARATORS.length; i++) {
			final String constructorArgument = new String(new byte[]{SEPARATORS[i]}, Charset.forName("ISO-8859-1"));
			assertExpectedIllegalArgumentExceptionThrown(constructorArgument);
		}
	}
	
	@Test
	public void tokenCanBeAnyOtherUsAsciiCharacters() throws Throwable {
		String constructorArgument = "!#$%&'*+-.0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ^_`abcdefghijklmnopqrstuvwxyz|~";
		Token token = new Token(constructorArgument);
		assertEquals(constructorArgument, token.toString());
	}
	
	@Test
	public void tokenCanBeSingleCharacter() throws Throwable {
		String constructorArgument = "a";
		Token token = new Token(constructorArgument);
		assertEquals(constructorArgument, token.toString());
	}

	private void assertExpectedIllegalArgumentExceptionThrown(final String constructorArgument) throws Throwable {
		IllegalTokenException exception = shouldThrow("Using constructor argument [" + constructorArgument + "]", IllegalTokenException.class, new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				new Token(constructorArgument);
				return null;
			}
		});
		assertEquals("[" + constructorArgument + "] is not a valid Token - must match [a-zA-Z0-9!#\\$%&'\\*\\+\\-\\.\\^_`\\|~]+", exception.getMessage());
	}
}
