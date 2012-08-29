package subtitle;

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
