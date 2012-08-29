package subtitle.filter;

import subtitle.Entry;

public class OffsetFilter extends SubtitleFilter {

    private final long offset;

    public OffsetFilter(long offset) {
        this.offset = offset;
    }

    @Override
    protected void filter() {
        while (hasNextEntry()) {
            Entry current = nextEntry();
            current.offset(offset);
        }
    }
}
