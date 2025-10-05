package algorithms;

import metrics.PerformanceTracker;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {53, 32, 8, 10, 2};
        PerformanceTracker t = new PerformanceTracker();
        new BubbleSort(t).sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}