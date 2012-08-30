package subtitle.filter;

import subtitle.Entry;

public class MaxFilter extends SubtitleFilter {

    private final long maxTime;

    public MaxFilter(long maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    protected void filter() {
        while (hasNextEntry()) {
            Entry next = nextEntry();
            if (next.lenght() > maxTime) {
                removeLastEntry();
            }
        }
    }
}
