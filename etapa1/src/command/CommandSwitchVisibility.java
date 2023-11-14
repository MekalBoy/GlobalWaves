package command;

import data.Library;
import data.Playlist;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandSwitchVisibility extends Command {
    int playlistId;

    public CommandSwitchVisibility() {}

    public CommandSwitchVisibility(String command, String username, int timestamp, int playlistId) {
        super(command, username, timestamp);
        this.playlistId = playlistId;
    }

    @Override
    public ResponseMsg processCommand() {
        String message = "Visibility status updated successfully to ";

        if (Library.instance.getPlaylists().size() > playlistId - 1) {
            message = "The specified playlist ID is too high.";
        } else {
            Playlist playlist = Library.instance.getPlaylists().get(playlistId - 1);

            message += playlist.isPrivate() ? "false." : "true.";
        }

        return new ResponseMsg(this, message);
    }
}
