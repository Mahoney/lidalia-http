package uk.org.lidalia.http.api.headerfield;

import uk.org.lidalia.http.api.Text;
import uk.org.lidalia.lang.Immutable;

public interface HeaderFieldValue extends Immutable {

	Text toText();
}
