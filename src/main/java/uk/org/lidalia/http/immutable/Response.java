package uk.org.lidalia.http.immutable;

import org.apache.commons.lang.StringUtils;

import uk.org.lidalia.http.exception.InvalidResponseException;

public class Response implements uk.org.lidalia.http.Response {

	private final ResponseHeader header;
	private final ResponseBody body;
	
	public Response(String responseString) throws InvalidResponseException {
		try {
			String headerString = StringUtils.substringBefore(responseString, "\r\n\r\n") + "\r\n";
			String bodyString = StringUtils.substringAfter(responseString, "\r\n\r\n");
			this.header = new ResponseHeader(headerString);
			this.body = ResponseBody.parse(bodyString);
		} catch (Exception e) {
			throw new InvalidResponseException(responseString, e);
		}
	}
	
	public Response(ResponseHeader header, ResponseBody body) {
		super();
		this.header = header;
		this.body = body;
	}

	/* (non-Javadoc)
	 * @see uk.org.lidalia.httpcache.immutable.Message#getHeader()
	 */
	public ResponseHeader getHeader() {
		return header;
	}
	
	/* (non-Javadoc)
	 * @see uk.org.lidalia.httpcache.immutable.Message#getBody()
	 */
	public ResponseBody getBody() {
		return body;
	}

	@Override
	public String toString() {
		return header + "\r\n" + body;
	}
}
