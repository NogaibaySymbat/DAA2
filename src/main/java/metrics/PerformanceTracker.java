package metrics;

public class PerformanceTracker {
    private long comparisons;
    private long swaps;
    private long arrayReads;
    private long arrayWrites;
    private long nanoseconds;

    public void reset(){
        comparisons = swaps = arrayReads = arrayWrites = nanoseconds = 0L;
    }

    public void addComparison(){
        comparisons++;
    }
    public void addSwaps(){
        swaps++;
    }
    public void addReads(int k){
        arrayReads += k;
    }
    public void addWrites(int k){
        arrayWrites += k;
    }

    public void setTimeNanos(long ns){
        this.nanoseconds = ns;
    }

    public long getComparisons(){
        return comparisons;
    }
    public long getSwaps(){
        return swaps;
    }
    public long getArrayReads(){
        return arrayReads;
    }
    public long getArrayWrites(){
        return arrayWrites;
    }
    public long getNanoseconds(){
        return nanoseconds;
    }
}
