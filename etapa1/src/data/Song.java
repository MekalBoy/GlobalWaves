package data;

import fileio.input.SongInput;
import functionality.SearchBar;
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

    private int nrLikes = 0;

    public Song() {
    }

    public Song(final SongInput input) {
        this.name = input.getName();
        this.duration = input.getDuration();
        this.album = input.getAlbum();
        this.tags = new ArrayList<String>(input.getTags());
        this.lyrics = input.getLyrics();
        this.genre = input.getGenre();
        this.releaseYear = input.getReleaseYear();
        this.artist = input.getArtist();
    }

    public Song(final String name, final int duration, final String album,
                final ArrayList<String> tags, final String lyrics, final String genre,
                final int releaseYear, final String artist) {
        super(name, duration);
        this.album = album;
        this.tags = new ArrayList<String>(tags);
        this.lyrics = lyrics;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.artist = artist;
    }

    @Override
    public final SearchBar.SearchType getType() {
        return SearchBar.SearchType.SONG;
    }
}
