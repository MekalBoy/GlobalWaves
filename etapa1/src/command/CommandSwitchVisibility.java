package command;

import data.Library;
import data.Playlist;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandSwitchVisibility extends Command {
    int playlistId;

    public CommandSwitchVisibility() {
    }

    public CommandSwitchVisibility(final String command, final String username,
                                   final int timestamp, final int playlistId) {
        super(command, username, timestamp);
        this.playlistId = playlistId;
    }

    @Override
    public final ResponseMsg processCommand() {
        String message = "Visibility status updated successfully to ";

        MusicPlayer player = Library.instance.seekUser(username).getPlayer();

        if (player.getCreatedPlaylists().size() <= playlistId - 1) {
            message = "The specified playlist ID is too high.";
        } else {
            Playlist playlist = player.getCreatedPlaylists().get(playlistId - 1);

            playlist.SwitchVisibility();

            message += playlist.isPrivate() ? "private." : "public.";
        }

        return new ResponseMsg(this, message);
    }
}
