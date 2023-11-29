package command;

import data.Library;
import data.Playlist;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandCreatePlaylist extends Command {
    private String playlistName;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        if (Library.instance.seekPlaylist(this.playlistName) != null) {
            message = "A playlist with the same name already exists.";
        } else {
            Playlist newPlaylist = new Playlist(this.playlistName, this.username);
            Library.instance.seekUser(this.username).getPlayer().addToCreatedPlaylists(newPlaylist);
            Library.instance.addPlaylist(newPlaylist);
            message = "Playlist created successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
