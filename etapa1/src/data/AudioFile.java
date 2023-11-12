package data;

import functionality.SearchBar;
import lombok.Getter;

@Getter
public class AudioFile implements ISelectable {
    protected String name;
    protected int duration;

    public AudioFile() {}

    public AudioFile(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    @Override
    public SearchBar.SearchType getType() {
        return null;
    }

    @Override
    public boolean isCollection() {
        return false;
    }
}
