package uk.org.lidalia.http;

import uk.org.lidalia.CanBeMadeImmutable;

public interface Header extends CanBeMadeImmutable {

	HeaderFields getHeaderFields();

}
