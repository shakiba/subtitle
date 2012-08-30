package subtitle;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import subtitle.filter.SubtitleFilter;
import subtitle.reader.SubtitleReader;
import subtitle.writer.SubtitleWriter;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length != 5) {
            feedback();
            feedback("ARGS:");
            feedback("  <Reader>" + "  <Filter>;<Filter>;..." + "  <Writer>"
                    + "  <input/file>|\"stdin\"" + "  <output/file>|\"stdout\"");

            feedback();
            feedback("READERS:");
            for (Help help : Help.readers) {
                feedback("  " + help.toString());
            }

            feedback();
            feedback("WRITERS:");
            for (Help help : Help.writers) {
                feedback("  " + help.toString());
            }

            feedback();
            feedback("FILTERS:");
            for (Help help : Help.filters) {
                feedback("  " + help.toString());
            }

            feedback();
            return;
        }

        String readerClass = args[0];
        String filterClasses = args[1];
        String writerClass = args[2];
        String input = args[3];
        String output = args[4];

        SubtitleReader reader = obj(readerClass, SubtitleReader.class, "Reader");

        SubtitleFilter filter = null;
        for (String filterClass : filterClasses.split("\\;")) {
            if (Util.is(filterClass)) {
                SubtitleFilter next = obj(filterClass, SubtitleFilter.class,
                        "Filter");
                if (filter != null) {
                    filter.next(next);
                } else {
                    filter = next;
                }
            }
        }

        SubtitleWriter writer = obj(writerClass, SubtitleWriter.class, "Writer");

        go(reader, filter, writer, input, output);
    }

    public static void go(SubtitleReader reader, SubtitleFilter filter,
            SubtitleWriter writer, String input, String output)
            throws Exception {

        Subtitle subtitle = reader.read(input).subtitle();

        feedback("Read: " + subtitle.entries().size());
        if (subtitle.entries().size() > 0) {
            feedback("First: " + subtitle.entries().get(0));
            feedback("Last:  "
                    + subtitle.entries().get(subtitle.entries().size() - 1));
        }

        if (filter != null) {
            filter.filter(subtitle);
        }

        feedback("Write: " + subtitle.entries().size());

        if (subtitle.entries().size() > 0) {
            feedback("First: " + subtitle.entries().get(0));
            feedback("Last:  "
                    + subtitle.entries().get(subtitle.entries().size() - 1));
        }

        writer.subtitle(subtitle).write(output);
    }

    private static ScriptEngineManager factory = new ScriptEngineManager();
    private static ScriptEngine engine = factory.getEngineByName("JavaScript");

    private static <E> E obj(String name, Class<?> base, String sufix)
            throws ScriptException {
        return obj(name, base.getPackage(), sufix);
    }

    @SuppressWarnings("unchecked")
    private static <E> E obj(String name, Package pkg, String sufix)
            throws ScriptException {
        name = name.trim();
        if (!name.matches("^\\w+\\(.*")) {
            name = name.replaceFirst("(^\\w+)", "$1()");
        }
        name = pkg.getName() + "." + name.replaceFirst("(\\W)", sufix + "$1");
        feedback("Creating: " + name);
        return (E) engine.eval("new Packages." + name);
    }

    static void feedback() {
        System.err.println();
    }

    static void feedback(Object msg) {
        System.err.println(msg);
    }
}