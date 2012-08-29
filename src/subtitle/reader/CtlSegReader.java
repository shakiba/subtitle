package subtitle.reader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import subtitle.Entry;
import subtitle.Util;

public class CtlSegReader extends SubtitleReader {

    public CtlSegReader() {
    }

    @Override
    public void line(String line) {
        line = Util.clean(line);
        Matcher matcher;
        if ((matcher = linePattern.matcher(line)).matches()) {
            long start = Long.valueOf(matcher.group(2)) * 10;
            long end = Long.valueOf(matcher.group(3)) * 10;

            Entry entry = new Entry();
            entry.startEnd(start, end);

            addEntry(entry);

        }
    }

    public static final Pattern linePattern = Pattern
            .compile("(\\S+) (\\d+) (\\d+) (\\S+)");
}