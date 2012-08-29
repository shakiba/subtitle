package subtitle;

import java.util.Collections;

public class SortFilter extends SubtitleFilter {

    @Override
    protected void filter() {
        Collections.sort(subtitle.entries());
    }
}
