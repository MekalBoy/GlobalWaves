package data;

import fileio.input.EpisodeInput;

public class Episode extends AudioFile {
    public String description;

    public Episode() {}

    public Episode(String name, int duration, String description) {
        super(name, duration);
        this.description = description;
    }

    public Episode(EpisodeInput input) {
        this.name = input.getName();
        this.duration = input.getDuration();
        this.description = input.getDescription();
    }
}
