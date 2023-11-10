package data;

import fileio.input.LibraryInput;
import fileio.input.SongInput;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Library {
    private List<Song> songs;
    private List<Playlist> playlists;
    private List<Podcast> podcasts;

    public static Library instance;

    private Library() {}

    public Library(LibraryInput input) {
        this.songs = new ArrayList<Song>();
        for (SongInput songInput : input.getSongs()) {
            this.songs.add(new Song(songInput));
        }
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    public void addPodcasts(Podcast podcast) {
        podcasts.add(podcast);
    }
}
