package data;

import functionality.SearchBar;
import lombok.Getter;

@Getter
public class AudioFile implements ISelectable {
    protected String name;
    protected int duration;

    public AudioFile() {
    }

    public AudioFile(final String name, final int duration) {
        this.name = name;
        this.duration = duration;
    }

    /**
     * Function is called for differentiating between SONG, PLAYLIST or PODCAST.
     * This also means that in this current implementation there is no proper return for EPISODE
     * except null.
     * @return SearchBar.SearchType
     */
    @Override
    public SearchBar.SearchType getType() {
        return null;
    }

    @Override
    public final boolean isCollection() {
        return false;
    }

    @Override
    public final AudioFile getNextAfter(final AudioFile file) {
        return null;
    }
}
