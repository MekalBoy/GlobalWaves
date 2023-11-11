package data;

import fileio.input.LibraryInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Library {
    private List<User> users;
    private List<Song> songs;
    private List<Playlist> playlists;
    private List<Podcast> podcasts;

    public static Library instance;

    private Library() {}

    public Library(LibraryInput input) {
        this.users = new ArrayList<User>();
        for (UserInput userInput : input.getUsers()) {
            this.users.add(new User(userInput));
        }
        this.songs = new ArrayList<Song>();
        for (SongInput songInput : input.getSongs()) {
            this.songs.add(new Song(songInput));
        }
        this.podcasts = new ArrayList<Podcast>();
        for (PodcastInput podcastInput : input.getPodcasts()) {
            this.podcasts.add(new Podcast(podcastInput));
        }
        if (instance == null) instance = this;
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
