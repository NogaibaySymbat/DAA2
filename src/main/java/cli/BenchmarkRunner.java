package cli;

import algorithms.BubbleSort;
import metrics.PerformanceTracker;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {
    public static void main(String[] args) throws Exception {
        int[] sizes = {100, 1_000, 10_000, 100_000};
        String[] dists = {"random", "sorted", "reversed", "nearly_sorted"};

        try (PrintWriter out = new PrintWriter("benchmark-bubblesort.csv")) {
            out.println("algo,n,dist,run,time_ms,comparisons,swaps,reads,writes");

            for (int n : sizes) {
                for (String dist : dists) {

                    if (n >= 100_000 && ("RANDOM".equals(dist) || "REVERSED".equals(dist))) {
                        System.out.printf("skip n=%d dist=%s (too slow for BubbleSort)%n", n, dist);
                        continue;
                    }

                    int runs = 1;
                    for (int run = 1; run <= runs; run++) {
                        System.out.printf("run n=%d dist=%s try=%d ...%n", n, dist, run);

                        int[] a = makeArray(n, dist, 42L + n + run);
                        PerformanceTracker t = new PerformanceTracker();
                        BubbleSort sorter = new BubbleSort(t);

                        long t0 = System.nanoTime();
                        sorter.sort(a);
                        long t1 = System.nanoTime();
                        t.setTimeNanos(t1 - t0);

                        int[] b = a.clone();
                        Arrays.sort(b);
                        if (!Arrays.equals(a, b)) throw new AssertionError("Not sorted!");

                        out.printf("BubbleSort,%d,%s,%d,%.3f,%d,%d,%d,%d%n",
                                n, dist, run, t.getNanoseconds() / 1_000_000.0,
                                t.getComparisons(), t.getSwaps(),
                                t.getArrayReads(), t.getArrayWrites());
                        out.flush();
                    }
                }
            }
        }
        System.out.println("CSV -> benchmark-bubblesort.csv");
    }

    private static int[] makeArray(int n, String dist, long seed) {
        Random r = new Random(seed);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt(1_000_000);

        if ("sorted".equals(dist)) {
            Arrays.sort(a);
        } else if ("reverse".equals(dist)) {
            Arrays.sort(a); reverse(a);
        } else if ("nearly_sorted".equals(dist)) {
            Arrays.sort(a);
            int k = Math.max(1, n / 100);
            for (int i = 0; i < k; i++) {
                int x = r.nextInt(n), y = r.nextInt(n);
                int t = a[x]; a[x] = a[y]; a[y] = t;
            }
        }
        return a;
    }

    private static void reverse(int[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
    }
}
