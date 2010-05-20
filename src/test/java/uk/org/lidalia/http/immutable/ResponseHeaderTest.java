package uk.org.lidalia.http.immutable;

import static uk.org.lidalia.testutils.Assert.shouldThrow;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.powermock.api.easymock.PowerMock.createMockAndExpectNew;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import java.util.concurrent.Callable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import uk.org.lidalia.http.exception.InvalidHeaderException;
import uk.org.lidalia.http.exception.InvalidResponseException;
import uk.org.lidalia.http.response.Reason;
import uk.org.lidalia.http.response.ResponseCode;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Response.class)
public class ResponseHeaderTest {

	@Test
	public void stringConstructorParsesCodeAndReasonAndConstructsHeaderFieldsWithEmptyString() throws Exception {
		Headers headerFieldsMock = createMockAndExpectNew(Headers.class, "header1: value\r\nheader2: value");
		replayAll();

		Response header = new Response("HTTP/1.1 200 OK here\r\nheader1: value\r\nheader2: value\r\n\r\n");
		assertSame(ResponseCode.OK, header.getCode());
		assertEquals(new Reason("OK here"), header.getReason());
		assertSame(headerFieldsMock, header.getHeaders());

		verifyAll();
	}
	
	@Test
	public void stringConstructorCanHaveNoCRLFAfterLastHeader() throws Exception {
		Headers headerFieldsMock = createMockAndExpectNew(Headers.class, "header1: value\r\nheader2: value");
		replayAll();

		Response header = new Response("HTTP/1.1 200 OK here\r\nheader1: value\r\nheader2: value\r\n\r\n");
		assertSame(ResponseCode.OK, header.getCode());
		assertEquals(new Reason("OK here"), header.getReason());
		assertSame(headerFieldsMock, header.getHeaders());

		verifyAll();
	}
	
	@Test
	public void stringConstructorCanHaveEmptyReason() throws Throwable {
		Headers headerFieldsMock = createMockAndExpectNew(Headers.class, "header1: value\r\nheader2: value");
		replayAll();

		Response header = new Response("HTTP/1.1 200 \r\nheader1: value\r\nheader2: value\r\n\r\n");
		assertSame(ResponseCode.OK, header.getCode());
		assertEquals(new Reason(""), header.getReason());
		assertSame(headerFieldsMock, header.getHeaders());

		verifyAll();
	}

	@Test
	public void stringConstructorCanHaveEmptyHeaders() throws Throwable {
		Headers headerFieldsMock = createMockAndExpectNew(Headers.class, "");
		replayAll();
		
		Response header = new Response("HTTP/1.1 200 OK here\r\n\r\n");
		assertSame(ResponseCode.OK, header.getCode());
		assertEquals(new Reason("OK here"), header.getReason());
		assertSame(headerFieldsMock, header.getHeaders());
		
		verifyAll();
	}
	
	@Test
	public void stringConstructorCanHaveEmptyReasonAndEmptyHeaders() throws Throwable {
		Headers headerFieldsMock = createMockAndExpectNew(Headers.class, "");
		replayAll();

		Response header = new Response("HTTP/1.1 200 \r\n\r\n");
		assertSame(ResponseCode.OK, header.getCode());
		assertEquals(new Reason(""), header.getReason());
		assertSame(headerFieldsMock, header.getHeaders());

		verifyAll();
	}

	@Test
	public void stringConstructorThrowsInvalidHeaderExceptionWhenFirstElementInStatusLineIsNotHTTPVersion() throws Throwable {
		final String headerString = "blah 200 OK";
		assertStringConstructorThrowsInvalidHeaderException(headerString);
	}

	@Test
	public void stringConstructorThrowsInvalidHeaderExceptionWhenOnlyOneElementInStatusLine() throws Throwable {
		final String headerString = "HTTP/1.1";
		assertStringConstructorThrowsInvalidHeaderException(headerString);
	}

	@Test
	public void stringConstructorThrowsInvalidHeaderExceptionWhenNoSpaceAfterCode() throws Throwable {
		final String headerString = "HTTP/1.1 200";
		assertStringConstructorThrowsInvalidHeaderException(headerString);
	}

	@Test
	public void stringConstructorThrowsInvalidHeaderExceptionForFourDigitResponseCode() throws Throwable {
		final String headerString = "HTTP/1.1 0200 OK";
		assertStringConstructorThrowsInvalidHeaderException(headerString);
	}
	
	private void assertStringConstructorThrowsInvalidHeaderException(final String headerString) throws Throwable {
		InvalidResponseException exception = shouldThrow(InvalidResponseException.class, new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				new Response(headerString + "\r\n\r\n");
				return null;
			}
		});
		
		InvalidHeaderException headerException = (InvalidHeaderException) exception.getCause();

		assertEquals("Unable to parse [" + headerString + "] into a valid HTTP Header", headerException.getMessage());
		assertSame(IllegalArgumentException.class, headerException.getCause().getClass());
		assertEquals("[" + headerString + "] must match ^HTTP/1.1 (\\d\\d\\d) ((?:.|\u0085)*)(?:\\r\\n)?((?:.|\u0085|\r|\n)*)$", headerException.getCause().getMessage());
	}

}
