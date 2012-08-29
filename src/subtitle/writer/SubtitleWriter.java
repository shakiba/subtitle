package subtitle.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import subtitle.Subtitle;

public abstract class SubtitleWriter {

    protected Subtitle subtitle;

    public SubtitleWriter subtitle(Subtitle subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public final void write(String fileName) throws IOException {
        if ("stdout".equals(fileName)) {
            write(System.out);
        } else {
            write(new File(fileName));
        }
    }

    public final void write(OutputStream out) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(out);
        write(writer);
        writer.flush();
    }

    public final void write(File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        write(writer);
        writer.flush();
        writer.close();
    }

    protected void write(Writer out) throws IOException {
        PrintWriter writer = new PrintWriter(out);
        write(writer);
        writer.flush();
    }

    protected void write(PrintWriter out) throws IOException {

    }

}