package subtitle.filter;

import subtitle.Entry;

public class NoEmptyFilter extends SubtitleFilter {

    @Override
    protected void filter() {
        while (hasNextEntry()) {
            Entry next = nextEntry();
            if (next.isEmpty()) {
                removeLastEntry();
            }
        }
    }
}
