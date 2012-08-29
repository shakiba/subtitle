package subtitle.filter;

import java.util.LinkedList;
import java.util.List;

import subtitle.Entry;

public class HeadFilter extends SubtitleFilter {

    private final int n;

    public HeadFilter() {
        this(10);
    }

    public HeadFilter(int n) {
        this.n = n;
    }

    @Override
    protected void filter() {
        List<Entry> entries = subtitle.entries();
        if (entries.size() > 0 && n != 0) {
            if (n > 0) {
                entries = head(n, entries);
            } else {
                // n is negative
                entries = TailFilter.tail(entries.size() + n, entries);
            }
            subtitle.entries(new LinkedList<Entry>(entries));
        }
    }

    public static List<Entry> head(int n, List<Entry> entries) {
        n = Math.min(n, entries.size());
        return entries.subList(0, n);
    }
}
