package data;

import fileio.input.LibraryInput;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Library {
    private List<User> users;
    private List<Song> songs;
    private List<Playlist> playlists;
    private List<Podcast> podcasts;

    public static Library instance;

    private Library() {}

    public Library(LibraryInput input) {
        this.users = input.getUsers().stream().map(User::new).collect(Collectors.toList());
        this.songs = input.getSongs().stream().map(Song::new).collect(Collectors.toList());
        this.podcasts = input.getPodcasts().stream().map(Podcast::new).collect(Collectors.toList());
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

    public User seekUser(String username) {
        for (User user : this.users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }
}
