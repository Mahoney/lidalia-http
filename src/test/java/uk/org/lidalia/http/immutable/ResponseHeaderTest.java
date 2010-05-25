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
import uk.org.lidalia.http.immutable.ResponseHeader;
import uk.org.lidalia.http.response.Reason;
import uk.org.lidalia.http.response.ResponseCode;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ResponseHeader.class)
public class ResponseHeaderTest {

	@Test
	public void stringConstructorParsesCodeAndReasonAndConstructsHeaderFieldsWithEmptyString() throws Exception {
		HeaderFields headerFieldsMock = createMockAndExpectNew(HeaderFields.class, "header1: value\r\nheader2: value\r\n");
		replayAll();

		ResponseHeader header = new ResponseHeader("HTTP/1.1 200 OK here\r\nheader1: value\r\nheader2: value\r\n");
		assertSame(ResponseCode.OK, header.getCode());
		assertEquals(new Reason("OK here"), header.getReason());
		assertSame(headerFieldsMock, header.getHeaderFields());

		verifyAll();
	}
	
	@Test
	public void stringConstructorCanHaveNoCRLFAfterLastHeader() throws Exception {
		HeaderFields headerFieldsMock = createMockAndExpectNew(HeaderFields.class, "header1: value\r\nheader2: value");
		replayAll();

		ResponseHeader header = new ResponseHeader("HTTP/1.1 200 OK here\r\nheader1: value\r\nheader2: value");
		assertSame(ResponseCode.OK, header.getCode());
		assertEquals(new Reason("OK here"), header.getReason());
		assertSame(headerFieldsMock, header.getHeaderFields());

		verifyAll();
	}
	
	@Test
	public void stringConstructorCanHaveEmptyReason() throws Throwable {
		HeaderFields headerFieldsMock = createMockAndExpectNew(HeaderFields.class, "header1: value\r\nheader2: value\r\n");
		replayAll();

		ResponseHeader header = new ResponseHeader("HTTP/1.1 200 \r\nheader1: value\r\nheader2: value\r\n");
		assertSame(ResponseCode.OK, header.getCode());
		assertEquals(new Reason(""), header.getReason());
		assertSame(headerFieldsMock, header.getHeaderFields());

		verifyAll();
	}

	@Test
	public void stringConstructorCanHaveEmptyHeaders() throws Throwable {
		HeaderFields headerFieldsMock = createMockAndExpectNew(HeaderFields.class, "");
		replayAll();
		
		ResponseHeader header = new ResponseHeader("HTTP/1.1 200 OK here\r\n");
		assertSame(ResponseCode.OK, header.getCode());
		assertEquals(new Reason("OK here"), header.getReason());
		assertSame(headerFieldsMock, header.getHeaderFields());
		
		verifyAll();
	}
	
	@Test
	public void stringConstructorCanHaveEmptyReasonAndEmptyHeaders() throws Throwable {
		HeaderFields headerFieldsMock = createMockAndExpectNew(HeaderFields.class, "");
		replayAll();

		ResponseHeader header = new ResponseHeader("HTTP/1.1 200 \r\n");
		assertSame(ResponseCode.OK, header.getCode());
		assertEquals(new Reason(""), header.getReason());
		assertSame(headerFieldsMock, header.getHeaderFields());

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
		InvalidHeaderException exception = shouldThrow(InvalidHeaderException.class, new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				new ResponseHeader(headerString);
				return null;
			}
		});

		assertEquals("Unable to parse [" + headerString + "] into a valid HTTP Header", exception.getMessage());
		assertSame(IllegalArgumentException.class, exception.getCause().getClass());
		assertEquals("[" + headerString + "] must match ^HTTP/1.1 (\\d\\d\\d) ((?:.|\u0085)*)(?:\\r\\n)?((?:.|\u0085|\r|\n)*)$", exception.getCause().getMessage());
	}

}
