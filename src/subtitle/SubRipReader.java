package subtitle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubRipReader extends SubtitleReader {

    private Entry current = new Entry();
    private State state = State.created;

    @Override
    public void line(String line) {
        line = Util.clean(line);
        Matcher matcher;
        if (Util.isEmpty(line)) {
            if (state != State.created) {
                current = new Entry();
                state = State.created;
            }

        } else if (state == State.created && line.matches("\\d+")) {
            // current.number(Integer.valueOf(line));
            state = State.numbered;

        } else if (state == State.numbered
                && (matcher = intervalPattern.matcher(line)).matches()) {
            long start = Entry.hmsl(matcher, 1, 2, 3, 4);
            long end = Entry.hmsl(matcher, 5, 6, 7, 8);
            current.time(start, end);
            state = State.timed;

        } else if (state == State.timed) {
            current.addLines(line);

            state = State.texting;
            addEntry(current);

        } else if (state == State.texting) {
            current.addLines(line);

        } else {
            throw new RuntimeException(line);
        }
    }

    public static final String timePattern = "(\\d{1,2})\\:(\\d{1,2})\\:(\\d{1,2})\\,(\\d{1,3})";
    public static final Pattern intervalPattern = Pattern.compile(timePattern
            + "\\s+-->\\s+" + timePattern);

    enum State {
        created, numbered, timed, texting
    }

}