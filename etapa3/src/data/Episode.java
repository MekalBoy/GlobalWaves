package data;

import fileio.input.EpisodeInput;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Episode extends AudioFile {
    private String description;

    public Episode() {
    }

    public Episode(final String name, final int duration, final String description) {
        super(name, duration);
        this.description = description;
    }

    public Episode(final EpisodeInput input) {
        this.name = input.getName();
        this.duration = input.getDuration();
        this.description = input.getDescription();
    }

    @Override
    public final SearchType getType() {
        return SearchType.EPISODE;
    }

    @Override
    public final String toString() {
        return name + " - " + description;
    }
}
