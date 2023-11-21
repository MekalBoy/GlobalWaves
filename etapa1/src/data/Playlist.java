package data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Playlist implements ISelectable {
    private String name, owner;
    private boolean isPrivate;
    private List<Song> songList = new ArrayList<Song>();
    private int followers;

    public Playlist(final String name, final String owner) {
        this.name = name;
        this.owner = owner;
        this.isPrivate = false;  // playlist is public when created
    }

    public Playlist(final Playlist original) {
        this.name = original.getName();
        this.owner = original.getOwner();
        this.isPrivate = original.isPrivate();
        this.songList = new ArrayList<Song>(original.getSongList());
        this.followers = original.getFollowers();
    }

    @Getter @Setter
    public static class PlaylistInfo {
        private String name;
        private List<String> songs;
        private String visibility;
        private int followers;

        public PlaylistInfo(final Playlist playlist) {
            this.name = playlist.getName();
            this.songs = playlist.getSongList().stream().map(Song::getName).toList();
            this.visibility = playlist.isPrivate() ? "private" : "public";
            this.followers = playlist.getFollowers();
        }
    }

    /**
     * Toggles the playlist between public and private depending on its previous state.
     */
    public void switchVisibility() {
        isPrivate = !isPrivate;
    }

    /**
     * Adds or removes a song from the playlist and returns a confirmation boolean.
     * @return true if the song was added; false if the song was removed.
     */
    public boolean addRemove(final Song song) {
        if (this.songList.contains(song)) {
            this.songList.remove(song);
            return false;
        } else {
            this.songList.add(song);
            return true;
        }
    }

    @Override
    public final Song getNextAfter(final AudioFile file) {
        Song song = (Song) file;
        int index = songList.indexOf(song);
        if (index == -1 || index == songList.size() - 1) {
            return null;
        }
        return songList.get(index + 1);
    }

    @Override
    public final Song getPrevBefore(final AudioFile file) {
        Song song = (Song) file;
        int index = songList.indexOf(song);
        if (index == -1 || index == 0) {
            return null;
        }
        return songList.get(index - 1);
    }

    @Override
    public final SearchType getType() {
        return ISelectable.SearchType.PLAYLIST;
    }

    @Override
    public final boolean isCollection() {
        return true;
    }
}
