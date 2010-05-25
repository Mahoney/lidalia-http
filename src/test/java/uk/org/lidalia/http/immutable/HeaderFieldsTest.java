package uk.org.lidalia.http.immutable;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.org.lidalia.http.HeaderFieldTypeRegistry;
import uk.org.lidalia.http.Text;

public class HeaderFieldsTest {

	@Test
	public void stringConstructorWithNormalHeaderFormat() throws Exception {
		String input = "header1: value1\r\nheader2: value2\r\n";
		HeaderFields headers = new HeaderFields(input);
		assertEquals(2, headers.size());
		assertEquals(new Text("value1"), headers.get(HeaderFieldTypeRegistry.get("header1")));
		assertEquals(new Text("value2"), headers.get(HeaderFieldTypeRegistry.get("header2")));
	}
	
	@Test
	public void stringConstructorWithLinearWhitespaceHeaderFormat() throws Exception {
		String input = "header1: value\r\n \tand more value\r\nheader2: value2\r\n";
		HeaderFields headers = new HeaderFields(input);
		assertEquals(2, headers.size());
		assertEquals(new Text("value and more value"), headers.get(HeaderFieldTypeRegistry.get("header1")));
		assertEquals(new Text("value2"), headers.get(HeaderFieldTypeRegistry.get("header2")));
	}
}
