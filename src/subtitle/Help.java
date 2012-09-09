package subtitle;

import java.util.ArrayList;
import java.util.List;

import subtitle.filter.ExcludeFilter;
import subtitle.filter.HeadFilter;
import subtitle.filter.JoinContinueFilter;
import subtitle.filter.MaxFilter;
import subtitle.filter.MinFilter;
import subtitle.filter.NoDialogFilter;
import subtitle.filter.NoEmptyFilter;
import subtitle.filter.NoExplainFilter;
import subtitle.filter.OffsetFilter;
import subtitle.filter.PadFilter;
import subtitle.filter.SortFilter;
import subtitle.filter.TailFilter;
import subtitle.filter.TrimNameFilter;
import subtitle.reader.MicroDVDReader;
import subtitle.reader.SubRipReader;
import subtitle.writer.MicroDVDWriter;
import subtitle.writer.SubRipWriter;
import subtitle.writer.TimeWriter;

public class Help {
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
        String prefix = "";
        if (clazz != null) {
            prefix = clazz.getSimpleName().replace("Filter", "")
                    .replace("Writer", "").replace("Reader", "")
                    + params;
        }
        return String.format("%-16s %s", prefix, desc);
    }

    static List<Help> filters = new ArrayList<Help>();
    static List<Help> readers = new ArrayList<Help>();
    static List<Help> writers = new ArrayList<Help>();
    static {
        filters.add(new Help(HeadFilter.class, "(n)",
                "Include only first n subtitle. Use -n to exclude."));
        filters.add(new Help(TailFilter.class, "(n)",
                "Include only last n subtitle. Use -n to exclude."));
        filters.add(new Help(""));
        filters.add(new Help(OffsetFilter.class, "(t)",
                "Move each subtitle t milliseconds."));
        filters.add(new Help(PadFilter.class, "(t)",
                "Add t milliseconds before and after each subtitle."));
        filters.add(new Help(PadFilter.class, "(tb, ta)",
                "Add tb milliseconds before and ta after each subtitle."));
        filters.add(new Help(""));
        filters.add(new Help(MaxFilter.class, "(t)",
                "Exclude subtitles which are more than t milliseconds."));
        filters.add(new Help(MinFilter.class, "(t)",
                "Exclude subtitles which are less than t milliseconds."));
        filters.add(new Help(ExcludeFilter.class, "(tf)",
                "Exclude subtitles which appears after tf milliseconds."));
        filters.add(new Help(ExcludeFilter.class, "(tf, tt)",
                "Exclude subtitles which appears between tf and tt milliseconds."));
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
        filters.add(new Help(TrimNameFilter.class,
                "Remove `NAME: ` from beginning of each line."));

        readers.add(new Help(SubRipReader.class, "SubRip (.srt) format reader."));
        readers.add(new Help(MicroDVDReader.class, "(r)",
                "MicroDVD (.sub) format reader, r is frame rate."));

        writers.add(new Help(SubRipWriter.class, "SubRip (.srt) format writer."));
        writers.add(new Help(MicroDVDWriter.class, "(r)",
                "MicroDVD (.sub) format writer, r is frame rate."));

        writers.add(new Help(
                TimeWriter.class,
                "(p, m)",
                "Print each entry as `start length stop`. Values are printed as `milliseconds * m / 1000` with `p` precision."));
        writers.add(new Help(TimeWriter.class, "(...).text(j)",
                "Use `j` to join subtitle lines and append them to times"));

    }

}
