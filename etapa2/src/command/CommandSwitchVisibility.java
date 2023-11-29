package command;

import data.Library;
import data.Playlist;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandSwitchVisibility extends Command {
    private int playlistId;

    @Override
    public final ResponseMsg processCommand() {
        String message = "Visibility status updated successfully to ";

        if (!Library.instance.seekUser(this.username).isOnline()) {
            message = this.username
                    + " is offline.";
        } else {
            MusicPlayer player = Library.instance.seekUser(username).getPlayer();

            if (player.getCreatedPlaylists().size() <= playlistId - 1) {
                message = "The specified playlist ID is too high.";
            } else {
                Playlist playlist = player.getCreatedPlaylists().get(playlistId - 1);

                playlist.switchVisibility();

                message += playlist.isPrivate()
                        ? "private."
                        : "public.";
            }
        }

        return new ResponseMsg(this, message);
    }
}
