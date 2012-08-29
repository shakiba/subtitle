package subtitle.filter;

import subtitle.Entry;

public class MinFilter extends SubtitleFilter {

    private final long minTime;

    public MinFilter(long minTime) {
        this.minTime = minTime;
    }

    @Override
    protected void filter() {
        while (hasNextEntry()) {
            Entry next = nextEntry();
            if (next.interval() < minTime) {
                removeLastEntry();
            }
        }
    }
}
