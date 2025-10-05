package algorithms;

import metrics.PerformanceTracker;

public class BubbleSort {
    private final PerformanceTracker tracker;
    public BubbleSort(PerformanceTracker tracker) {
        this.tracker = tracker;
    }

    public void sort(int[] a) {
        if (a == null) throw new IllegalArgumentException("Array cannot be null");
        tracker.reset();
        int n = a.length;
        int newn;
        do {
            boolean swapped = false;
            newn = 0;
            for (int i = 1; i < n; i++) {
                int left  = a[i - 1]; tracker.addReads(1);
                int right = a[i];     tracker.addReads(1);
                tracker.addComparison();
                if (left > right){
                    a[i - 1] = right; tracker.addWrites(1);
                    a[i]     = left;  tracker.addWrites(1);
                    tracker.addSwaps();
                    swapped = true;
                    newn = i;
                }
            }
            n = newn;
            if (!swapped) break;
        } while (n > 1);
    }
}
