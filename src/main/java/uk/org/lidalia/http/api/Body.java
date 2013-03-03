package uk.org.lidalia.http.api;

import java.io.InputStream;

import uk.org.lidalia.http.api.immutable.ImmutableBody;
import uk.org.lidalia.http.api.mutable.MutableBody;
import uk.org.lidalia.lang.CanBeMadeImmutable;
import uk.org.lidalia.lang.CanBeMadeMutable;

public interface Body extends CanBeMadeImmutable<ImmutableBody>, CanBeMadeMutable<MutableBody> {

    InputStream getInputStream();

    byte[] getBytes();
}
