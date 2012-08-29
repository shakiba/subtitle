package subtitle;

public class PadFilter extends SubtitleFilter {

    private final long start;
    private final long end;

    public PadFilter(long pad) {
        this.start = pad;
        this.end = pad;
    }

    public PadFilter(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void filter() {
        while (hasNextEntry()) {
            Entry current = nextEntry();
            current.interval(current.start() - start, current.interval()
                    + start + end);
        }
    }
}
