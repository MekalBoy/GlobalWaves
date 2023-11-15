package data;

import functionality.SearchBar;
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

    public Playlist(String name, String owner) {
        this.name = name;
        this.owner = owner;
        this.isPrivate = false;  // playlist is public when created
    }

    public Playlist(Playlist original) {
        this.name = original.getName();
        this.owner = original.getOwner();
        this.isPrivate = original.isPrivate();
        this.songList = new ArrayList<Song>(original.getSongList());
        this.followers = original.getFollowers();
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

    public Song getNextAfter(AudioFile file) {
        Song song = (Song) file;
        int index = songList.indexOf(song);
        if (index == -1 || index == songList.size() - 1) return null;
        return songList.get(index + 1);
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
