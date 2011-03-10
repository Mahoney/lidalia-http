package uk.org.lidalia.http.api.response;

import static uk.org.lidalia.http.api.response.Reason.Reason;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.Validate;

import uk.org.lidalia.http.api.response.Reason;
import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.MapUtils;
import uk.org.lidalia.lang.Utils;
import uk.org.lidalia.lang.WrappedValue;

public final class Code extends WrappedValue<Integer> implements Immutable {
	
	private static final ConcurrentMap<Integer, Code> codes = new ConcurrentHashMap<Integer, Code>();
	
	public static final Code OK = Code(200, Reason("OK"));
	
	private final Reason defaultReason;	
	
	private Code(Integer code, Reason defaultReason) {
		super(code);
		Validate.isTrue(code >= 100);
		Validate.isTrue(code <= 999);
		this.defaultReason = defaultReason;
	}
	
	public Integer toInteger() {
		return wrappedValue;
	}

	public Reason getDefaultReason() {
		return defaultReason;
	}
	
	@Override
	public Code toImmutable() {
		return this;
	}

	public static Set<Code> values() {
		return Collections.unmodifiableSet(new HashSet<Code>(codes.values()));
	}

	public static Code Code(Integer code) {
		return Code(code, null);
	}

	public static Code Code(Integer code, Reason defaultReason) {
		Code result = codes.get(code);
		if (result == null) {
			result = MapUtils.putIfAbsentReturningValue(codes, code, new Code(code, defaultReason));
		}
		return result;
	}

}
