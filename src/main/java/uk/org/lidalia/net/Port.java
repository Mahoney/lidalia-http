package uk.org.lidalia.net;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.Validate;

import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.Utils;
import uk.org.lidalia.lang.WrappedValue;

public final class Port extends WrappedValue<Integer> implements Immutable {
	
	private static final ConcurrentMap<Integer, Port> ports = new ConcurrentHashMap<Integer, Port>();
	
	public static Port Port(Integer portNumber) {
		return Utils.putIfAbsentReturningObjectInMap(ports, portNumber, new Port(portNumber));
	}

	private Port(Integer portNumber) {
		super(portNumber);
		Validate.notNull(portNumber, "portNumber is null");
		Validate.isTrue(portNumber >= 0, "portNumber < 0");
		Validate.isTrue(portNumber <= 65535, "portNumber > 65535");
	}
	
	public Integer getPortNumber() {
		return wrappedValue;
	}
	
	@Override
	public Port toImmutable() {
		return this;
	}
}
