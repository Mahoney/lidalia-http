package uk.org.lidalia.http.api;

import java.io.InputStream;

import uk.org.lidalia.http.api.immutable.ImmutableBody;
import uk.org.lidalia.lang.CanBeMadeImmutable;

public interface Body extends CanBeMadeImmutable {

	@Override
	ImmutableBody toImmutable();
	
	InputStream getInputStream();
	
	byte[] getBytes();
}
