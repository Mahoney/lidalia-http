package uk.org.lidalia.http.immutable.response;

public class ResponseBody implements uk.org.lidalia.http.response.ResponseBody {
	
	private final String body;

	private ResponseBody(String body) {
		this.body = body;
	}

	public static ResponseBody parse(String bodyString) {
		return bodyString == null ? null : new ResponseBody(bodyString);
	}

	public String getBody() {
		return body;
	}
	
	@Override
	public String toString() {
		return body;
	}

}