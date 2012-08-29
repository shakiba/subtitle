package subtitle;

import java.util.LinkedList;

public class Subtitle {
    private LinkedList<Entry> entries = new LinkedList<Entry>();

    public LinkedList<Entry> entries() {
        return entries;
    }

    public Subtitle entries(LinkedList<Entry> entries) {
        this.entries = entries;
        return this;
    }
}
