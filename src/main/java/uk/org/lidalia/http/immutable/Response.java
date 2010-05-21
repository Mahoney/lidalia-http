package uk.org.lidalia.http.immutable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.exception.InvalidResponseException;
import uk.org.lidalia.http.response.Reason;
import uk.org.lidalia.http.response.ResponseCode;
import uk.org.lidalia.http.response.ResponseCodeRegistry;

public class Response implements uk.org.lidalia.http.response.Response {

	private static final String ANYTHING = "(?:.|\u0085|\r|\n)*";
	private static final String CODE = "(\\d\\d\\d)";
	private static final String REASON = "((?:.|\u0085)*)";
	private static final String CRLF = "\\r\\n";
	private static final String OPTIONAL_CRLF = "(?:" + CRLF + ")?";
	private static final String HEADERS = "(" + ANYTHING + ")";
	private static final String HEADER_REGEX = "^HTTP/1.1 " + CODE + " " + REASON + OPTIONAL_CRLF + HEADERS + "$";
	
	private static final Pattern STATUS_LINE_PATTERN = Pattern.compile(HEADER_REGEX);
	
	private final ResponseCode code;
	private final Reason reason;
	private final Headers headers;
	private final ResponseBody body;
	
	public Response(String responseString) throws InvalidResponseException {
		try {
			Validate.isTrue(responseString.contains("\r\n\r\n"), "A Response must have a double CLRF after the header");
			
			String headerString = StringUtils.substringBefore(responseString, "\r\n\r\n");
			Matcher headerMatcher = STATUS_LINE_PATTERN.matcher(headerString);
			Validate.isTrue(headerMatcher.matches(), "[" + headerString + "] must match " + HEADER_REGEX);
			code = ResponseCodeRegistry.get(Integer.valueOf(headerMatcher.group(1)));
			reason = new Reason(headerMatcher.group(2));
			headers = new Headers(headerMatcher.group(3));
			
			String bodyString = StringUtils.substringAfter(responseString, "\r\n\r\n");
			this.body = ResponseBody.parse(bodyString);
		} catch (Exception e) {
			throw new InvalidResponseException(responseString, e);
		}
	}
	
	public Response(ResponseCode code, Reason reason, Headers headers, ResponseBody body) throws InvalidResponseException {
		Validate.notNull(code);
		Validate.notNull(reason);
		Validate.notNull(headers);
		this.code = code;
		this.reason = reason;
		this.headers = headers;
		this.body = body;
	}
	
	/* (non-Javadoc)
	 * @see uk.org.lidalia.httpcache.immutable.Message#getBody()
	 */
	public ResponseBody getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "HTTP/1.1 " + code + " " + reason + "\r\n" + headers + "\r\n" + (body != null ? body : "");
	}

	public ResponseCode getCode() {
		return code;
	}

	public Reason getReason() {
		return reason;
	}

	public Headers getHeaders() {
		return headers;
	}
}
