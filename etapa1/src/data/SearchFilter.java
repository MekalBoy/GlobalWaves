package data;

import java.util.ArrayList;
import java.util.List;

public class SearchFilter {
    public String name = "", album = "", lyrics = "", genre = "", artist = "", owner = "";
    public String releaseYear = "";
    public List<String> tags = new ArrayList<String>();

    public SearchFilter() {}

    public SearchFilter(String name, String album, String lyrics, String genre, String artist,
                        String releaseYear, List<String> tags) {
        this.name = name;
        this.album = album;
        this.lyrics = lyrics;
        this.genre = genre;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.tags = tags;
    }

    public SearchFilter(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "name='" + name + '\'' +
                ", album='" + album + '\'' +
                ", lyrics='" + lyrics + '\'' +
                ", genre='" + genre + '\'' +
                ", artist='" + artist + '\'' +
                ", owner='" + owner + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", tags=" + tags +
                '}';
    }
}