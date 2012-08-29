package subtitle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public abstract class SubtitleReader {

    protected Subtitle subtitle = new Subtitle();

    public final SubtitleReader read(String fileName) throws IOException {
        if ("stdin".equals(fileName)) {
            return read(System.in);
        } else {
            return read(new File(fileName));
        }
    }

    public final SubtitleReader read(InputStream stream) throws IOException {
        return read(new InputStreamReader(stream));
    }

    public final SubtitleReader read(File file) throws IOException {
        return read(new FileReader(file));
    }

    public final SubtitleReader read(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        String line;
        while ((line = br.readLine()) != null) {
            line(line);
        }
        return this;
    }

    public Subtitle subtitle() {
        return subtitle;
    }

    protected abstract void line(String line);

    protected void addEntry(Entry entry) {
        subtitle.entries().add(entry);
    }

}
