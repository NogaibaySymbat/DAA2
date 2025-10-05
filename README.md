ASSIGNMENT 2
--
Name:Nogaibay Symbat
Group: se-2402
Course:Design and Analysis of Algorithms
-------
Introduction
------
In this assignment, I implemented Bubble Sort in Java, verified correctness with unit tests, and collected performance metrics.The project is a small Maven setup and includes a simple CLI benchmark.

-----
Implementation
------
Algorithm: algorithms/BubbleSort.java (in-place)
Optimizations I used:

Early exit — stop if a full pass makes no swaps.

Last-swap boundary — the next pass only iterates up to the last swap index.

Metrics: metrics/PerformanceTracker.java
I track comparisons, swaps, reads, writes, and store elapsed time (ns).

Benchmark: cli/BenchmarkRunner.java
Generates inputs, runs the sort, prints progress, and saves results to benchmark-bubblesort.csv.

How it works (brief): on each pass I compare adjacent elements and swap if they are out of order. With the optimizations above, nearly sorted arrays finish much faster.

------
Testing
----
Empty array, single element, duplicates

Already sorted (best case) — should make 0 swaps

Reversed (worst case)

Random arrays — result is compared with Arrays.sort(...)

-----
Benchmark Setup
---
Sizes: 100, 1,000, 10,000 (and optionally 100,000 for light cases)

Input types: RANDOM, SORTED, REVERSED, NEARLY_SORTED (~1% noise)

Repeats: 5 runs per size by default; 1 run for 100,000 to avoid very long execution

----
Conclusion
---
I implemented Bubble Sort with two simple optimizations, gathered metrics, and validated correctness with tests.The algorithm is fine for small or nearly sorted inputs, but on large random/reversed arrays its Θ(n²) time makes it impractical.
