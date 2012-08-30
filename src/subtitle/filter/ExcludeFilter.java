package subtitle.filter;

import subtitle.Entry;

public class ExcludeFilter extends SubtitleFilter {

    private final long from;
    private final long to;

    public ExcludeFilter(long from) {
        this(from, Long.MAX_VALUE);
    }

    public ExcludeFilter(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected void filter() {
        while (hasNextEntry()) {
            Entry next = nextEntry();
            if (from < next.end() && next.start() < to) {
                removeLastEntry();
            }
        }
    }

}