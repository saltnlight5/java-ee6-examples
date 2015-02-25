/*
 *  Zemian Deng 2014
 */

package zemian.service.util;

import org.hamcrest.CoreMatchers;
import org.junit.*;
import static org.junit.Assert.*;
import static zemian.service.util.Tuple.tuple;

/**
 *
 * @author zedeng
 */
public class TupleTest {
    @Test
    public void testTuple() {
        Tuple<String, String> t = Tuple.tuple("A", "B");
        assertThat(t.getA(), CoreMatchers.is("A"));
        assertThat(t.getB(), CoreMatchers.is("B"));
        
        Tuple<String, Integer> t2 = Tuple.tuple("A", 123);
        assertThat(t2.getA(), CoreMatchers.is("A"));
        assertThat(t2.getB(), CoreMatchers.is(123));
        
        Tuple<Tuple<String, String>, String> t3 = tuple(tuple("A", "B"), "C");
        assertThat(t3.getA(), CoreMatchers.is(tuple("A", "B")));
        assertThat(t3.getB(), CoreMatchers.is("C"));
    }
}
