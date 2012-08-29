package subtitle;

import java.util.List;
import java.util.regex.Pattern;

public class NoExplainFilter extends SubtitleFilter {

    @Override
    protected void filter() {
        while (hasNextEntry()) {
            List<String> lines = nextEntry().lines();
            for (int l = 0; l < lines.size(); l++) {
                String line = lines.get(l);
                if (descPattern.matcher(line).matches()) {
                    lines.remove(l--);
                }
            }
        }
    }

    private static final Pattern descPattern = Pattern
            .compile("\\s*\\[[^\\]]*\\]\\s*");

}
