package uk.org.lidalia.http.immutable;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.createMockAndExpectNew;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;
import static uk.org.lidalia.testutils.Assert.shouldThrow;

import java.util.concurrent.Callable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import uk.org.lidalia.http.exception.InvalidResponseException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Response.class, ResponseBody.class})
public class ResponseTest {

	@Test
	public void constructedWithNullHeaderThrowsIllegalArgumentException() throws Throwable {
		IllegalArgumentException exception = shouldThrow(IllegalArgumentException.class, new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				new Response(null, null);
				return null;
			}
		});
		
		assertEquals("A Response cannot have a null header", exception.getMessage());
	}
	
	@Test
	public void constructByStringWithNoDoubleLineBreakThrowsInvalidResponseException() throws Throwable {
		InvalidResponseException exception = shouldThrow(InvalidResponseException.class, new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				new Response("HTTP/1.1 200 OK\r\n");
				return null;
			}
		});
		
		assertEquals("Invalid response: HTTP/1.1 200 OK\r\n", exception.getMessage());
		assertSame(IllegalArgumentException.class, exception.getCause().getClass());
		assertEquals("A Response must have a double CLRF after the header", exception.getCause().getMessage());
	}
	
	@Test
	public void constructByStringDelegatesToHeaderAndBodyConstructByString() throws Exception {
		ResponseHeader headerMock = createMockAndExpectNew(ResponseHeader.class, "header");
		ResponseBody bodyMock = createMock(ResponseBody.class);
		mockStatic(ResponseBody.class);
		expect(ResponseBody.parse("body")).andReturn(bodyMock);
		replayAll();
		
		Response response = new Response(
				"header\r\n" +
				"\r\n" +
				"body");
		
		assertSame(headerMock, response.getHeader());
		assertSame(bodyMock, response.getBody());
		
		verifyAll();
	}
	
	@Test
	public void toStringReturnsSemanticallySameResponseAsStringConstructorTakes() throws Exception {
		String responseString = "HTTP/1.1 200 OK\r\n" +
				"\r\n" +
				"somebody";
		Response response = new Response(responseString);
		assertEquals(responseString, response.toString());
	}
}
