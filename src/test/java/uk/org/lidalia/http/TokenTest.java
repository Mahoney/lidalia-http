package uk.org.lidalia.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static uk.org.lidalia.testutils.Assert.shouldThrow;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.concurrent.Callable;

import org.junit.Test;

public class TokenTest {
	
	@Test
	public void tokenCannotHaveNonUsAsciiCharacters() throws Throwable {
		for (byte i = -128; i < 0; i++) {
			final String constructorArgument = new String(new byte[]{i}, Charset.forName("ISO-8859-1"));
			assertExpectedIllegalArgumentExceptionThrown("[" + constructorArgument + "] is not a valid Token - contains [" + i + "], must only contain non CTL US-ASCII characters", constructorArgument);
		}
	}
	
	@Test
	public void tokenCannotHaveControlCharacters() throws Throwable {
		for (byte i = 0; i <= 31; i++) {
			final String constructorArgument = new String(new byte[]{i}, Charset.forName("ISO-8859-1"));
			assertExpectedIllegalArgumentExceptionThrown("[" + constructorArgument + "] is not a valid Token - contains [" + i + "], must only contain non CTL US-ASCII characters", constructorArgument);
		}
		final String constructorArgument = new String(new byte[]{127}, Charset.forName("ISO-8859-1"));
		assertExpectedIllegalArgumentExceptionThrown("[" + constructorArgument + "] is not a valid Token - contains [127], must only contain non CTL US-ASCII characters", constructorArgument);
	}

	@Test
	public void tokenCannotBeEmpty() throws Throwable {
		assertExpectedIllegalArgumentExceptionThrown("Token must contain at least one character", "");
	}
	
	@Test
	public void tokenCanBeAnyOtherUsAsciiCharacters() throws Throwable {
		String constructorArgument = "!#$%&'*+-.0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ^_`abcdefghijklmnopqrstuvwxyz|~";
		Token token = new Token(constructorArgument);
		assertEquals(constructorArgument, token.toString());
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
			assertExpectedIllegalArgumentExceptionThrown("[" + constructorArgument + "] is not a valid Token - contains [" + SEPARATORS[i] + "], must not contain any of the characters ()<>@,;:\\\"/[]?={} \t", constructorArgument);
		}
	}

	private void assertExpectedIllegalArgumentExceptionThrown(final String exceptionMessage, final String constructorArgument) throws Throwable {
		IllegalArgumentException exception = shouldThrow(IllegalArgumentException.class, new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				new Token(constructorArgument);
				return null;
			}
		});
		assertEquals(exceptionMessage, exception.getMessage());
	}
	
	@Test
	public void regex() {
		byte start = -128;
		byte end = 127;
		byte[] allBytes = new byte[(end - start) + 1];
		for (int i = start; i <= end; i++) {
			allBytes[(i - start)] = (byte) i;
		}
		String allChars = new String(allBytes, Charset.forName("ISO-8859-1"));
		System.out.println(allChars);
		assertTrue("coudln't match " + allChars, allChars.matches("(.|\r|\n|\u0085)*"));
	}
}
