package uk.org.lidalia.http;

public interface HeaderFieldType {

	String getName();
	HeaderFieldValue getValue(String headerValue);

	static class UnknownHeaderFieldType implements HeaderFieldType {

		public UnknownHeaderFieldType(String headerName) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public HeaderFieldValue getValue(String headerValue) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
