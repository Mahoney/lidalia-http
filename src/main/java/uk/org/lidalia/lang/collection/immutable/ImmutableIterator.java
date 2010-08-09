package uk.org.lidalia.lang.collection.immutable;

import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.collection.Iterator;

public interface ImmutableIterator<E extends Immutable> extends Immutable, Iterator<E> {
	// same methods, different contracts
}
