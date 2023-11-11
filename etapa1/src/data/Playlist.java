package data;

import lombok.Getter;

import java.util.List;

@Getter
public class Playlist {
    private String name, owner;
    private boolean isPrivate;
    private List<Song> songList;

    public Playlist(String name, String owner) {
        this.name = name;
        this.owner = owner;
        this.isPrivate = false;  // playlist is public when created
    }

    public void SwitchVisibility() {
        isPrivate = !isPrivate;
    }
}
