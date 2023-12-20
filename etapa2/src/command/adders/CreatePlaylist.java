package command.adders;

import command.Command;
import command.response.ResponseMsg;
import data.Playlist;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePlaylist extends Command {
    private String playlistName;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        if (!library.seekUser(this.username).isOnline()) {
            message = this.username + " is offline.";
        } else {
            if (library.seekPlaylist(this.playlistName) != null) {
                message = "A playlist with the same name already exists.";
            } else {
                Playlist newPlaylist = new Playlist(this.playlistName, this.username);
                library.seekUser(this.username)
                        .getPlayer().addToCreatedPlaylists(newPlaylist);
                library
                        .addPlaylist(newPlaylist);
                message = "Playlist created successfully.";
            }
        }

        return new ResponseMsg(this, message);
    }
}
