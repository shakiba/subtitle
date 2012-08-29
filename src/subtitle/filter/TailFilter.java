package subtitle.filter;

import java.util.LinkedList;
import java.util.List;

import subtitle.Entry;

public class TailFilter extends SubtitleFilter {

    private final int n;

    public TailFilter() {
        this(10);
    }

    public TailFilter(int n) {
        this.n = n;
    }

    @Override
    protected void filter() {
        List<Entry> entries = subtitle.entries();
        if (entries.size() > 0 && n != 0) {
            if (n > 0) {
                entries = tail(n, entries);
            } else {
                // n is negative
                entries = HeadFilter.head(entries.size() + n, entries);
            }
            subtitle.entries(new LinkedList<Entry>(entries));
        }
    }

    public static List<Entry> tail(int n, List<Entry> entries) {
        n = Math.min(n, entries.size());
        return entries.subList(entries.size() - n, entries.size());
    }
}
