package subtitle.filter;

import java.util.List;

import subtitle.Entry;

public class TrimNameFilter extends SubtitleFilter {

    @Override
    protected void filter() {
        while (hasNextEntry()) {
            Entry next = nextEntry();
            List<String> lines = next.lines();
            for (int i = 0; i < lines.size(); i++) {
                lines.set(i, lines.get(i).replaceFirst(NAME, ""));
            }
        }
    }

    public static final String NAME = "^([A-Z]|\\W)+\\:";
}
