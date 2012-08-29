package subtitle.filter;

import subtitle.Entry;
import subtitle.Util;

public class NoDialogFilter extends SubtitleFilter {

    // TODO: how to split time?

    @Override
    protected void filter() {
        while (hasNextEntry()) {
            Entry next = nextEntry();
            boolean one = false;
            for (String line : next.lines()) {
                line = Util.clean(line);
                if (line.startsWith("- ") || line.matches("^[A-Z]+\\:.*")) {
                    if (one) {
                        removeLastEntry();
                        break;
                    } else {
                        one = true;
                    }
                }
            }
        }
    }

}
