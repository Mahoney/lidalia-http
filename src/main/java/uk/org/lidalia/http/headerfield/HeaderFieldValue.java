package uk.org.lidalia.http.headerfield;

import uk.org.lidalia.Immutable;
import uk.org.lidalia.http.Text;

public interface HeaderFieldValue extends Immutable {

	Text toText();
}
