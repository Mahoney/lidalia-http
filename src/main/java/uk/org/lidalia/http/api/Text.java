package uk.org.lidalia.http.api;

import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetEncoder;

import uk.org.lidalia.lang.CharSets;
import uk.org.lidalia.lang.WrappedString;

public class Text extends WrappedString {

	private static final int NULL = 0;
	private static final int HORIZONTAL_TAB = 9;
	private static final int LINE_FEED = 10;
	private static final int CARRIAGE_RETURN = 13;
	private static final int UNIT_SEPARATOR = 31;
	private static final int SPACE = 32;
	private static final int DEL = 127;

	public static Text Text(String text) {
		return new Text(text);
	}
	
	protected Text(String text) {
		super(text);
		CharsetEncoder encoder = CharSets.ISO_8859_1.newEncoder();
		CharBuffer charBuffer = CharBuffer.wrap(text);
		byte[] textAsBytes;
		try {
			textAsBytes = encoder.encode(charBuffer).array();
		} catch (CharacterCodingException e) {
			throw new IllegalArgumentException("[" + text + "] is not valid Text - is not ISO-8859-1", e);
		}
		for (int i = 0; i < textAsBytes.length; i++) {
			byte currentByte = textAsBytes[i];
			byte nextByte = textAsBytes.length > i + 1 ? textAsBytes[i + 1] : DEL;
			byte nextButOneByte = textAsBytes.length > i + 2 ? textAsBytes[i + 2] : DEL;
			if (isControlCharacter(currentByte)) {
				if (currentByte == CARRIAGE_RETURN && nextByte == LINE_FEED) {
					if (isWhiteSpace(nextButOneByte)) {
						i = i + 2;
						continue;
					}
				}
				throw new IllegalArgumentException("[" + text + "] is not valid Text - contains control characters");
			}
			
		}
	}

	private boolean isControlCharacter(byte b) {
		return (b > NULL && b <= UNIT_SEPARATOR && b != HORIZONTAL_TAB) || b == DEL;
	}
	
	private boolean isWhiteSpace(byte b) {
		return b == HORIZONTAL_TAB || b == SPACE;
	}

}
