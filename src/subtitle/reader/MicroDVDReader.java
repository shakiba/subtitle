package subtitle.reader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import subtitle.Entry;
import subtitle.Util;

public class MicroDVDReader extends SubtitleReader {

    private final int frameRate;

    public MicroDVDReader(int frameRate) {
        this.frameRate = frameRate;
    }

    @Override
    public void line(String line) {
        line = Util.clean(line);
        Matcher matcher;
        if (Util.isEmpty(line)) {

        } else if ((matcher = linePattern.matcher(line)).matches()) {
            long start = Long.valueOf(matcher.group(1)) / frameRate;
            long end = Long.valueOf(matcher.group(2)) / frameRate;
            String text = Util.clean(matcher.group(4));

            Entry entry = new Entry();
            entry.time(start, end);
            entry.setLines(text.split("\\|"));

            addEntry(entry);

        } else {
            throw new RuntimeException(line);
        }
    }

    public static final Pattern linePattern = Pattern
            .compile("\\{(\\d+)\\}\\{(\\d+)\\}(\\{[^}]+\\})?([^{}]*)");
}