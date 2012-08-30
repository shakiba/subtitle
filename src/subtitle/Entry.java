package subtitle;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class Entry implements Comparable<Entry> {
    // private Integer number;
    private long start, lenght;
    private List<String> lines = new ArrayList<String>();

    // private String file;

    // public Entry number(Integer number) {
    // this.number = number;
    // return this;
    // }
    // public Integer number() {
    // return number;
    // }

    public void join(Entry that) {
        this.startEnd(this.start, that.end());
        this.lines.addAll(that.lines);
    }

    public Entry startEnd(long start, long end) {
        start(start);
        lenght(end - start);
        return this;
    }

    public Entry startInterval(long start, long lenght) {
        start(start);
        lenght(lenght);
        return this;
    }

    public Entry start(long start) {
        this.start = Math.max(start, 0);
        return this;
    }

    public long start() {
        return start;
    }

    public Entry offset(long offset) {
        this.start += offset;
        return this;
    }

    public long end() {
        return start + lenght;
    }

    public Entry lenght(long lenght) {
        this.lenght = Math.max(lenght, 0);
        return this;
    }

    public long lenght() {
        return lenght;
    }

    public Entry addLines(String... lines) {
        for (String line : lines) {
            if (Util.is(line)) {
                this.lines.add(line);
            }
        }
        return this;
    }

    // "\\r?\\n"
    public Entry setLines(String... lines) {
        this.lines.clear();
        return addLines(lines);
    }

    public List<String> lines() {
        return lines;
    }

    public String joinLines(String joiner) {
        if (!Util.is(lines)) {
            return "";
        }
        String joined = lines.get(0);
        for (int i = 1; i < lines.size(); i++) {
            joined += joiner + lines.get(i);
        }
        return joined;
    }

    public boolean isEmpty() {
        if (Util.is(lines)) {
            for (String line : lines) {
                if (Util.is(line)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String text() {
        if (lines.size() == 0) {
            return "";
        }
        if (lines.size() == 1) {
            return lines.get(0);
        }
        StringBuilder text = new StringBuilder(lines.get(0));
        for (String line : lines.subList(1, lines.size())) {
            text.append("\n" + line);
        }
        return text.toString();
    }

    // public Entry file(String file) {
    // this.file = file;
    // return this;
    // }

    @Override
    public String toString() {
        return String.format("%8d %6d %8d %s", start(), lenght(), end(),
                joinLines("|"));
    }

    public static String hmsl(long millis, String millisSign) {
        long l = millis % 1000;
        millis /= 1000;

        long s = millis % 60;
        millis /= 60;

        long m = millis % 60;
        millis /= 60;

        long h = millis % 1000;

        return h + ":" + m + ":" + s + millisSign + l;
    }

    public static long hmsl(Matcher matcher, int h, int m, int s, int l) {
        return hmsl(matcher.group(h), matcher.group(m), matcher.group(s),
                matcher.group(l));
    }

    public static long hmsl(String hour, String min, String sec, String mill) {
        return (((intme(hour) * 60 + intme(min)) * 60) + intme(sec)) * 1000
                + intme(mill);
    }

    private static int intme(String str) {
        return Util.toInt(str, 0);
    }

    @Override
    public int compareTo(Entry that) {
        return (int) (this.start() - that.start());
    }
}