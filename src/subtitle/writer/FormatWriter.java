package subtitle.writer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import subtitle.Entry;

public class FormatWriter extends SubtitleWriter {

    private String format = "";
    private String lineJoiner;

    public FormatWriter sample() {
        return start("%ts.%tL ").lenght("%ts.%tL ").end("%ts.%tL ")
                .text("%s", "|");
    }

    public FormatWriter start(String format) {
        this.format += format.replace("%", "%1$");
        return this;
    }

    public FormatWriter end(String format) {
        this.format += format.replace("%", "%2$");
        return this;
    }

    public FormatWriter lenght(String format) {
        this.format += format.replace("%", "%3$");
        return this;
    }

    public FormatWriter text(String format, String lineJoiner) {
        this.format += format.replace("%", "%4$");
        this.lineJoiner = lineJoiner;
        return this;
    }

    @Override
    protected void write(PrintWriter out) throws IOException {
        List<Entry> list = subtitle.entries();
        for (Entry entry : list) {
            Calendar start = cal(entry.start());
            Calendar end = cal(entry.end());
            Calendar length = cal(entry.length());
            String text = lineJoiner != null ? " "
                    + entry.joinLines(lineJoiner) : "";
            out.println(String.format(format, start, end, length, text));
        }
    }

    private Calendar cal(long time) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"),
                Locale.ROOT);
        cal.setTimeInMillis(time);
        return cal;
    }

    Formatter f;
}