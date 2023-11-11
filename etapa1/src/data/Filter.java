package data;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    public String name = "", album = "", lyrics = "", genre = "", artist = "", owner = "";
    public String releaseYear = "";
    public List<String> tags = new ArrayList<String>();

    public Filter(String name, String album, String lyrics, String genre, String artist, String releaseYear, List<String> tags) {
        this.name = name;
        this.album = album;
        this.lyrics = lyrics;
        this.genre = genre;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.tags = tags;
    }

    public Filter(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }
}