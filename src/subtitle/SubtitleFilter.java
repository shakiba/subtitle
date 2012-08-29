package subtitle;

public abstract class SubtitleFilter {

    protected Subtitle subtitle;

    private SubtitleFilter next;

    public SubtitleFilter next(SubtitleFilter next) {
        if (this.next == null) {
            this.next = next;
        } else {
            this.next.next(next);
        }
        return this;
    }

    public final void filter(Subtitle subtitle) {
        this.subtitle = subtitle;
        filter();
        if (next != null) {
            next.filter(subtitle);
        }
    }

    protected abstract void filter();

    private int entryIndex = -1;

    protected boolean hasNextEntry() {
        if (subtitle.entries().size() > entryIndex + 1) {
            return true;
        }
        return false;
    }

    protected Entry nextEntry() {
        return subtitle.entries().get(++entryIndex);
    }

    protected Entry removeLastEntry() {
        return subtitle.entries().remove(entryIndex--);
    }
}