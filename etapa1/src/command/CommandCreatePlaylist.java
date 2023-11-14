package command;

import data.Library;
import data.Playlist;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandCreatePlaylist extends Command {
    private String playlistName;

    public CommandCreatePlaylist() {}

    public CommandCreatePlaylist(String command, String username, int timestamp, String playlistName) {
        super(command, username, timestamp);
        this.playlistName = playlistName;
    }

    @Override
    public ResponseMsg processCommand() {
        String message;

        if (Library.instance.seekPlaylist(this.playlistName) != null) {
            message = "A playlist with the same name already exists.";
        } else {
            Library.instance.addPlaylist(new Playlist(this.playlistName, this.username));
            message = "Playlist created successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
