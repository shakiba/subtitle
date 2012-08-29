package subtitle;

public class MaxFilter extends SubtitleFilter {

    private final long maxTime;

    public MaxFilter(long maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    protected void filter() {
        while (hasNextEntry()) {
            Entry next = nextEntry();
            if (next.interval() > maxTime) {
                removeLastEntry();
            }
        }
    }
}
