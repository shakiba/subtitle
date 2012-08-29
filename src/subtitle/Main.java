package subtitle;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Main {
    private static List<Help> filters = new ArrayList<Help>();
    private static List<Help> readers = new ArrayList<Help>();
    private static List<Help> writers = new ArrayList<Help>();
    static {
        filters.add(new Help(HeadFilter.class, "(n)", "Only first n subtitle."));
        filters.add(new Help(TailFilter.class, "(n)", "Only last n subtitle."));
        filters.add(new Help(""));
        filters.add(new Help(OffsetFilter.class, "(t)",
                "Move each subtitle t milli seconds."));
        filters.add(new Help(PadFilter.class, "(t)",
                "Adds t milli seconds to begining and end of each subtitle."));
        filters.add(new Help(PadFilter.class, "(ts, te)",
                "Adds ts milli seconds to start and and te to end of each subtitle."));
        filters.add(new Help(""));
        filters.add(new Help(MaxFilter.class, "(t)",
                "Exlude subtitles which are more than t milli seconds."));
        filters.add(new Help(MinFilter.class, "(t)",
                "Exlude subtitles which are less than t milli seconds."));
        filters.add(new Help(ExcludeFilter.class, "(s, e)",
                "Exlude subtitles which appears between ts and te milli seconds."));
        filters.add(new Help(""));
        filters.add(new Help(SortFilter.class, "Sort by start time."));
        filters.add(new Help(""));
        filters.add(new Help(NoEmptyFilter.class, "Exclude empty subtitle."));
        filters.add(new Help(NoExplainFilter.class,
                "Exclude [EXPLANATORY] subtitles."));
        filters.add(new Help(
                NoDialogFilter.class,
                "Exclude subtitles which contains a dialog like `ME: Hi|YOU: Bye!` or `- Hi|- Bye`."));
        filters.add(new Help(JoinContinueFilter.class,
                "Join each two subtitles if one ends with and next starts with `...`."));

        readers.add(new Help(SubRipReader.class, "SubRip (.srt) format reader."));
        readers.add(new Help(MicroDVDReader.class, "(r)",
                "MicroDVD (.sub) format reader, r is frame rate."));

        writers.add(new Help(SubRipWrite.class, "SubRip (.srt) format writer."));
        writers.add(new Help(MicroDVDWriter.class, "(r)",
                "MicroDVD (.sub) format writer, r is frame rate."));
    }

    static class Help {
        private final Class<?> clazz;
        private final String params;
        private final String desc;

        public Help(String desc) {
            this(null, "", desc);
        }

        public Help(Class<?> clazz, String desc) {
            this(clazz, "", desc);
        }

        public Help(Class<?> clazz, String params, String desc) {
            this.clazz = clazz;
            this.params = params;
            this.desc = desc;
        }

        @Override
        public String toString() {
            if (clazz == null) {
                return desc;
            }
            return String.format(
                    "%-16s %s",
                    clazz.getSimpleName().replace("Filter", "")
                            .replace("Writer", "").replace("Reader", "")
                            + params, desc);
        }
    }

    public static void main(String[] args) throws Exception {

        if (args.length != 5) {
            feedback();
            feedback("ARGS:");
            feedback("  <Reader>" + "  <Filter>;<Filter>;..." + "  <Writer>"
                    + "  <input/file>|\"stdin\"" + "  <output/file>|\"stdout\"");

            feedback();
            feedback("READERS:");
            for (Help help : readers) {
                feedback("  " + help.toString());
            }

            feedback();
            feedback("WRITERS:");
            for (Help help : writers) {
                feedback("  " + help.toString());
            }

            feedback();
            feedback("FILTERS:");
            for (Help help : filters) {
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

        SubtitleReader reader = obj(readerClass, "Reader");

        SubtitleFilter filter = null;
        for (String filterClass : filterClasses.split("\\;")) {
            if (Util.is(filterClass)) {
                SubtitleFilter next = obj(filterClass, "Filter");
                if (filter != null) {
                    filter.next(next);
                } else {
                    filter = next;
                }
            }
        }

        SubtitleWrite writer = obj(writerClass, "Writer");

        go(reader, filter, writer, input, output);
    }

    public static void go(SubtitleReader reader, SubtitleFilter filter,
            SubtitleWrite writer, String input, String output) throws Exception {

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

    @SuppressWarnings("unchecked")
    private static <E> E obj(String name, String sufix) throws ScriptException {
        name = name.trim();
        if (!name.endsWith(")")) {
            name += "()";
        }
        name = name.replaceFirst("\\(", sufix + "(");
        feedback("Creating: " + name);
        // name = "importPackage(Packages." + Main.class.getPackage().getName()
        // + "); new " + name;
        name = "new Packages." + Main.class.getPackage().getName() + "." + name;
        // feedback(name);
        return (E) engine.eval(name);
    }

    static void feedback() {
        System.err.println();
    }

    static void feedback(Object msg) {
        System.err.println(msg);
    }
}