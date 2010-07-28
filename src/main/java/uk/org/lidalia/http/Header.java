package uk.org.lidalia.http;

import uk.org.lidalia.MakeImmutable;

public interface Header extends MakeImmutable {

	HeaderFields getHeaderFields();

}
