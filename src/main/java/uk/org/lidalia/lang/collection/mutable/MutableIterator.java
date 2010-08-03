package uk.org.lidalia.lang.collection.mutable;

import uk.org.lidalia.lang.collection.Iterator;

public interface MutableIterator<E> extends Iterator<E> {

	void remove();
}
