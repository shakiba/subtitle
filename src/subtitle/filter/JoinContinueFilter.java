package subtitle.filter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import subtitle.Entry;
import subtitle.Util;

public class JoinContinueFilter extends SubtitleFilter {

    private Entry last;
    private String lastLine;

    @Override
    protected void filter() {
        while (hasNextEntry()) {
            Entry current = nextEntry();
            List<String> lines = current.lines();

            if (Util.isEmpty(lines)) {
                last = null;
            }

            Matcher startM = startPattern.matcher(lines.get(0));
            boolean start = startM.matches();

            Matcher endM = endPattern.matcher(lines.get(lines.size() - 1));
            boolean end = endM.matches();

            if (start) {
                if (last != null) {
                    last.lines().set(last.lines().size() - 1, lastLine);
                    current.lines().set(0, startM.group(1));
                    last.join(current);
                    removeLastEntry();
                }
            }

            if (end) {
                lastLine = endM.group(1);
                last = current;
            } else {
                last = null;
            }
        }
    }

    private static final Pattern startPattern = Pattern
            .compile("^\\s*\\.{3}(.*)$");

    private static final Pattern endPattern = Pattern
            .compile("^(.*)\\.{3}\\s*$");
}
