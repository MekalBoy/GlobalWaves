package data;

import functionality.SearchBar;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Playlist implements ISelectable {
    private String name, owner;
    private boolean isPrivate;
    private List<Song> songList = new ArrayList<Song>();
    private int followers;

    public Playlist(String name, String owner) {
        this.name = name;
        this.owner = owner;
        this.isPrivate = false;  // playlist is public when created
    }

    @Getter @Setter
    public static class PlaylistInfo {
        String name;
        List<String> songs;
        String visibility;
        int followers;

        public PlaylistInfo() {}

        public PlaylistInfo(Playlist playlist) {
            this.name = playlist.getName();
            this.songs = playlist.getSongList().stream().map(Song::getName).toList();
            this.visibility = playlist.isPrivate() ? "private" : "public";
            this.followers = playlist.getFollowers();
        }
    }

    public void SwitchVisibility() {
        isPrivate = !isPrivate;
    }

    public boolean AddRemove(Song song) {
        if (this.songList.contains(song)) {
            this.songList.remove(song);
            return false;
        } else {
            this.songList.add(song);
            return true;
        }
    }

    @Override
    public SearchBar.SearchType getType() {
        return SearchBar.SearchType.PLAYLIST;
    }

    @Override
    public boolean isCollection() {
        return true;
    }
}
