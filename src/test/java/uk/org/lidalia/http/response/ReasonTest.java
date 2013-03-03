package uk.org.lidalia.http.response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static uk.org.lidalia.http.api.response.Reason.Reason;
import static uk.org.lidalia.test.ShouldThrow.shouldThrow;

import java.util.concurrent.Callable;

import org.junit.Test;

public class ReasonTest {

    @Test
    public void stringConstructorDoesNotAllowCROrLF() {
        assertExceptionThrown("glah\r\n blah");
    }

    @Test
    public void testSimpleTrue() {
        String s = "humbapumpa jim";
        assertTrue(s.matches(".*(jim|joe).*"));
        s = "humbapumpa jom";
        assertFalse(s.matches(".*(jim|joe).*"));
        s = "humbaPumpa joe";
        assertTrue(s.matches(".*(jim|joe).*"));
        s = "humbapumpa joejim";
        assertTrue(s.matches(".*(j|o).*"));
    }


    private void assertExceptionThrown(final String string) {
        IllegalArgumentException exception = shouldThrow(IllegalArgumentException.class, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                Reason(string);
                return null;
            }
        });
        assertThat(exception.getMessage(), is("[" + string + "] contains a CR or LF character"));
    }
}
