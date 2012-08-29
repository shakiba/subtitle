package subtitle.writer;

import java.io.IOException;
import java.io.PrintWriter;

import subtitle.Entry;

public class MicroDVDWriter extends SubtitleWriter {

    private final int frameRate;

    public MicroDVDWriter(int frameRate) {
        this.frameRate = frameRate;
    }

    @Override
    protected void write(PrintWriter out) throws IOException {
        for (Entry entry : subtitle.entries()) {
            out.println(String.format("{%d}{%d}%s", entry.start() * frameRate,
                    entry.end() * frameRate, entry.joinLines("|")));
        }
    }
}