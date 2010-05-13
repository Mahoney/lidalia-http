package uk.org.lidalia.http.immutable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import uk.org.lidalia.http.ResponseCode;
import uk.org.lidalia.http.immutable.ResponseHeader;


public class HTTPResponseHeaderTest {

	@Test
	public void codeAndReasonParsed() throws Exception {
		ResponseHeader header = new ResponseHeader("HTTP/1.1 200 OK here\r\n");
		assertEquals("OK here", header.getReason());
		assertSame(ResponseCode.OK, header.getCode());
	}
}
