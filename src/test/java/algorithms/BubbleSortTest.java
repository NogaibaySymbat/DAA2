package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BubbleSortTest {

    private int[] copy(int[] a){ return Arrays.copyOf(a, a.length); }

    @Test
    void handlesNull() {
        PerformanceTracker t = new PerformanceTracker();
        BubbleSort s = new BubbleSort(t);
        assertThrows(IllegalArgumentException.class, () -> s.sort(null));
    }

    @Test
    void emptyArray() {
        PerformanceTracker t = new PerformanceTracker();
        BubbleSort s = new BubbleSort(t);
        int[] a = new int[0];
        s.sort(a);
        assertArrayEquals(new int[0], a);
    }

    @Test
    void singleElement() {
        PerformanceTracker t = new PerformanceTracker();
        BubbleSort s = new BubbleSort(t);
        int[] a = new int[]{7};
        s.sort(a);
        assertArrayEquals(new int[]{7}, a);
    }

    @Test
    void duplicates() {
        PerformanceTracker t = new PerformanceTracker();
        BubbleSort s = new BubbleSort(t);
        int[] a = new int[]{5,5,5,5,5};
        s.sort(a);
        assertArrayEquals(new int[]{5,5,5,5,5}, a);
    }

    @Test
    void sortedBestCaseEarlyExit() {
        PerformanceTracker t = new PerformanceTracker();
        BubbleSort s = new BubbleSort(t);
        int[] a = new int[]{1,2,3,4,5,6};
        s.sort(a);
        assertArrayEquals(new int[]{1,2,3,4,5,6}, a);
        assertEquals(0, t.getSwaps());
    }

    @Test
    void reverseWorstCase() {
        PerformanceTracker t = new PerformanceTracker();
        BubbleSort s = new BubbleSort(t);
        int[] a = new int[]{6,5,4,3,2,1};
        s.sort(a);
        assertArrayEquals(new int[]{1,2,3,4,5,6}, a);
        assertTrue(t.getSwaps() > 0);
    }

    @Test
    void randomizedPropertyBased() {
        Random rnd = new Random(123);
        for (int n = 0; n < 200; n++) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = rnd.nextInt();
            int[] expected = copy(a);
            Arrays.sort(expected);
            PerformanceTracker t = new PerformanceTracker();
            new BubbleSort(t).sort(a);
            assertArrayEquals(expected, a);
        }
    }
}
