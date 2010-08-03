package uk.org.lidalia.lang.collection.immutable;

import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.collection.Collection;

public interface ImmutableCollection<E extends Immutable> extends Collection<E>, Immutable {

	ImmutableCollection<E> plus(E e);
	
	ImmutableCollection<E> minus(E e);

	ImmutableCollection<E> plus(Collection<? extends E> c);
	
	ImmutableCollection<E> minus(Collection<? extends E> c);
	
	ImmutableCollection<E> intersect(Collection<? extends E> c);
	
	@Override
	public ImmutableIterator<E> iterator();
}
