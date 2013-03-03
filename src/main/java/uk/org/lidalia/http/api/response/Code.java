package uk.org.lidalia.http.api.response;

import static uk.org.lidalia.http.api.response.Reason.Reason;
import static com.google.common.base.Optional.fromNullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.Validate;

import uk.org.lidalia.lang.Immutable;
import uk.org.lidalia.lang.WrappedValue;

public final class Code extends WrappedValue<Integer> implements Immutable<Code> {

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
        return getWrappedValue();
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
            final Code value = new Code(code, defaultReason);
            result = fromNullable(codes.putIfAbsent(code, value)).or(value);
        }
        return result;
    }

}
