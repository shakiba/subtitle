package subtitle;

import java.util.LinkedList;

public class TailFilter extends SubtitleFilter {

    private final int n;

    public TailFilter() {
        this(10);
    }

    public TailFilter(int n) {
        this.n = Math.max(n, 1);
    }

    @Override
    protected void filter() {
        LinkedList<Entry> entries = subtitle.entries();
        if (entries.size() > 0) {
            int m = Math.min(n, entries.size());
            subtitle.entries(new LinkedList<Entry>(entries.subList(
                    entries.size() - m, entries.size())));
        }
    }
}
