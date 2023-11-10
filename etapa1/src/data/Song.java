package data;

import fileio.input.SongInput;

import java.util.ArrayList;

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
        this.tags = input.getTags();
        this.lyrics = input.getLyrics();
        this.genre = input.getGenre();
        this.releaseYear = input.getReleaseYear();
        this.artist = input.getArtist();
    }
}
