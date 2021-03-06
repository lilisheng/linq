package com.bestvike.linq.enumerable;

import com.bestvike.linq.IEnumerable;
import com.bestvike.linq.Linq;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by 许崇雷 on 2018-05-11.
 */
public class AsEnumerableTest extends EnumerableTest {
    @Test
    public void SameResultsRepeatCallsIntQuery() {
        IEnumerable<Integer> q = Linq.asEnumerable(9999, 0, 888, -1, 66, -777, 1, 2, -12345)
                .where(x -> x > Integer.MIN_VALUE);

        Assert.assertEquals(q.asEnumerable(), q.asEnumerable());
    }

    @Test
    public void SameResultsRepeatCallsStringQuery() {
        IEnumerable<String> q = Linq.asEnumerable("!@#$%^", "C", "AAA", "", "Calling Twice", "SoS", Empty)
                .where(x -> !IsNullOrEmpty(x));

        Assert.assertEquals(q.asEnumerable(), q.asEnumerable());
    }

    @Test
    public void NullSourceAllowed() {
        IEnumerable<Integer> source = null;

        //not same to C#
        assertThrows(NullPointerException.class, () -> source.asEnumerable());
    }

    @Test
    public void OneElement() {
        IEnumerable<Integer> source = Linq.singleton(2);

        Assert.assertEquals(source, source.asEnumerable());
    }

    @Test
    public void SomeElements() {
        IEnumerable<Integer> source = Linq.asEnumerable(-5, 0, 1, -4, 3, null, 10);

        Assert.assertEquals(source, source.asEnumerable());
    }

    @Test
    public void SomeElementsRunOnce() {
        IEnumerable<Integer> source = Linq.asEnumerable(-5, 0, 1, -4, 3, null, 10);

        assertEquals(source, source.runOnce().asEnumerable());
    }
}
