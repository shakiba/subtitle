package subtitle.writer;

import java.io.IOException;
import java.io.PrintWriter;

import subtitle.Entry;

public class SubRipWriter extends SubtitleWriter {

    public SubRipWriter() {
    }

    @Override
    protected void write(PrintWriter out) throws IOException {
        int i = 1;
        for (Entry entry : subtitle.entries()) {
            out.println(i);
            out.println(Entry.hmsl(entry.start(), ",") + " --> "
                    + Entry.hmsl(entry.end(), ","));
            out.println(entry.joinLines("\n"));
            out.println();
            i++;
        }
    }
}