package data;

import lombok.Getter;

@Getter
public class AudioFile {
    protected String name;
    protected int duration;

    public AudioFile() {}

    public AudioFile(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }
}
