package data;

import fileio.input.LibraryInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class Library {
    private List<User> users;
    private List<Song> songs;
    private List<Playlist> playlists;
    private List<Podcast> podcasts;
    private List<Album> albums;

    public static Library instance;
    // each test needs a new library so the instance is remade each time
    // probably useless then, might change it in future iterations

    public Library(final LibraryInput input) {
        this.users = input.getUsers().stream().map(User::new).collect(Collectors.toList());
        this.songs = input.getSongs().stream().map(Song::new).collect(Collectors.toList());
        this.playlists = new ArrayList<Playlist>();
        this.podcasts = input.getPodcasts().stream().map(Podcast::new).collect(Collectors.toList());
        this.albums = new ArrayList<Album>();
        instance = this;
    }

    /**
     * Adds the song to the library's database.
     */
    public final void addSong(final Song song) {
        songs.add(song);
    }

    /**
     * Adds the playlist to the library's database.
     */
    public final void addPlaylist(final Playlist playlist) {
        this.playlists.add(playlist);
    }

    /**
     * Adds the podcast to the library's database.
     */
    public final void addPodcast(final Podcast podcast) {
        podcasts.add(podcast);
    }

    /**
     * Adds the album to the library's database.
     */
    public final void addAlbum(final Album album) {
        albums.add(album);
    }

    /**
     * Adds the user to the library's database.
     */
    public final void addUser(final User user) {
        users.add(user);
    }

    /**
     * Retrieves the User object from the library's database.
     * @param username username of the seeked user
     * @return User object or null
     */
    public final User seekUser(final String username) {
        for (User user : this.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Retrieves the Playlist object from the library's database.
     * @param playlistName name of the seeked playlist
     * @return Playlist object or null
     */
    public final Playlist seekPlaylist(final String playlistName) {
        for (Playlist playlist : this.playlists) {
            if (playlist.getName().equals(playlistName)) {
                return playlist;
            }
        }
        return null;
    }

    /**
     * Retrieves the Playlist object from the library's database.
     * @param albumName name of the seeked album
     * @return Album object or null
     */
    public final Album seekAlbum(final String albumName) {
        for (Album album : this.albums) {
            if (album.getName().equals(albumName)) {
                return album;
            }
        }
        return null;
    }
}
