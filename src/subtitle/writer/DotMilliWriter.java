package subtitle.writer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import subtitle.Entry;

public class DotMilliWriter extends SubtitleWriter {

    public static final String format = "%.3f %.3f %.3f";

    @Override
    protected void write(PrintWriter out) throws IOException {
        List<Entry> list = subtitle.entries();

        for (Entry subtitle : list) {
            out.println(String.format(format, (subtitle.start() / 1000d),
                    (subtitle.interval() / 1000d), (subtitle.end() / 1000d)));
        }
    }
}