package data;

import fileio.input.LibraryInput;
import fileio.input.SongInput;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<AudioFile> songs;
    private List<Playlist> playlists;
    private List<Podcast> podcasts;

    public Library() {}

    public Library(LibraryInput input) {
        this.songs = new ArrayList<AudioFile>();
        for (SongInput songInput : input.getSongs()) {
            this.songs.add(new Song(songInput));
        }
    }
}
