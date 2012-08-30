package subtitle.writer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import subtitle.Entry;

public class TimeWriter extends SubtitleWriter {

    private final String format;
    private final double multiply;
    private String lineJoiner;

    public TimeWriter() {
        this(3, 1);
    }

    public TimeWriter(int precision) {
        this(precision, 1);
    }

    public TimeWriter(int precision, double multiply) {
        this.format = "%.xf %.xf %.xf".replace("x", precision + "");
        this.multiply = multiply;
    }

    public TimeWriter text(String lineJoiner) {
        this.lineJoiner = lineJoiner;
        return this;
    }

    @Override
    protected void write(PrintWriter out) throws IOException {
        List<Entry> list = subtitle.entries();
        for (Entry entry : list) {
            String text = lineJoiner != null ? " "
                    + entry.joinLines(lineJoiner) : "";
            out.println(String.format(format, t(entry.start()),
                    t(entry.lenght()), t(entry.end()))
                    + text);
        }
    }

    private double t(long time) {
        return time * (multiply / 1000);
    }
}