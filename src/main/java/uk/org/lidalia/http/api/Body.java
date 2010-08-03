package uk.org.lidalia.http.api;

import java.io.OutputStream;
import java.util.List;

import uk.org.lidalia.http.api.immutable.ImmutableBody;
import uk.org.lidalia.lang.CanBeMadeImmutable;

public interface Body extends CanBeMadeImmutable {

	ImmutableBody toImmutable();
	
	OutputStream getOutputStream();
	
	List<Byte> getBytes();
}
