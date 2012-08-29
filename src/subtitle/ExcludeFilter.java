package subtitle;

public class ExcludeFilter extends SubtitleFilter {

    private final long start;
    private final long end;

    public ExcludeFilter(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void filter() {
        while (hasNextEntry()) {
            Entry next = nextEntry();
            if (start < next.end() && next.start() < end) {
                removeLastEntry();
            }
        }
    }

}