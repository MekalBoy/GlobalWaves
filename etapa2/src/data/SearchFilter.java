package data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class SearchFilter {
    private String name = "", album = "", lyrics = "", genre = "", artist = "", owner = "";
    private String releaseYear = "";
    private List<String> tags = new ArrayList<String>();

    public SearchFilter() {
    }

    public SearchFilter(final String name, final String album, final String lyrics,
                        final String genre, final String artist, final String releaseYear,
                        final List<String> tags) {
        this.name = name;
        this.album = album;
        this.lyrics = lyrics;
        this.genre = genre;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.tags = tags;
    }

    public SearchFilter(final String name, final String owner) {
        this.name = name;
        this.owner = owner;
    }
}
