package uk.org.lidalia.http.api.headerfield;

import uk.org.lidalia.Immutable;
import uk.org.lidalia.http.api.Text;

public interface HeaderFieldValue extends Immutable {

	Text toText();
}
