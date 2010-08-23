package uk.org.lidalia.net;

import static uk.org.lidalia.net.Port.Port;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

import org.apache.commons.lang.Validate;

import uk.org.lidalia.lang.Utils;
import uk.org.lidalia.lang.WrappedString;

public final class Scheme extends WrappedString {
	
	private static final String VALID_SCHEME_REGEX = "[a-zA-Z][a-zA-Z0-9$_@.&+\\-]*";
	private static final Pattern VALID_SCHEME_PATTERN = Pattern.compile(VALID_SCHEME_REGEX);

	private static final ConcurrentMap<String, Scheme> schemes = new ConcurrentHashMap<String, Scheme>();
	
	public static final Scheme HTTP = Scheme("http", Port(80));
	public static final Scheme HTTPS = Scheme("https", Port(443));
	public static final Scheme FTP = Scheme("ftp", Port(21));
	public static final Scheme SSH = Scheme("ssh", Port(22));
	public static final Scheme MAILTO = Scheme("mailto");
	public static final Scheme FILE = Scheme("file");
	
	private final Port defaultPort;
	
	public static Scheme Scheme(String schemeName) {
		return Scheme(schemeName, null);
	}
	
	public static Scheme Scheme(String schemeName, Port defaultPort) {
		return Utils.putIfAbsentReturningObjectInMap(schemes, schemeName, new Scheme(schemeName, defaultPort));		
	}
	
	private Scheme(String schemeName, Port defaultPort) {
		super(schemeName);
		Validate.isTrue(VALID_SCHEME_PATTERN.matcher(schemeName).matches(), "schemeName does not match " + VALID_SCHEME_REGEX);
		this.defaultPort = defaultPort;
	}
	
	public Port getDefaultPort() {
		return defaultPort;
	}
	
	@Override
	public Scheme toImmutable() {
		return this;
	}
}
