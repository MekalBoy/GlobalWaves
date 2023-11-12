package data;

import fileio.input.SongInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class Song extends AudioFile {
    private String album;
    private ArrayList<String> tags;
    private String lyrics;
    private String genre;
    private int releaseYear;
    private String artist;

    public Song() {}

    public Song(SongInput input) {
        this.name = input.getName();
        this.duration = input.getDuration();
        this.album = input.getAlbum();
        this.tags = new ArrayList<String>(input.getTags());
        this.lyrics = input.getLyrics();
        this.genre = input.getGenre();
        this.releaseYear = input.getReleaseYear();
        this.artist = input.getArtist();
    }

    public Song(String name, int duration, String album, ArrayList<String> tags, String lyrics, String genre, int releaseYear, String artist) {
        super(name, duration);
        this.album = album;
        this.tags = new ArrayList<String>(tags);
        this.lyrics = lyrics;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Song{" +
                "album='" + album + '\'' +
                ", tags=" + tags +
                ", lyrics='" + lyrics + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseYear=" + releaseYear +
                ", artist='" + artist + '\'' +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }
}
