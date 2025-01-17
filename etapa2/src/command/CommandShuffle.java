package command;

import command.response.ResponseMsg;
import data.ISelectable;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandShuffle extends Command {
    private int seed;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        if (!library.seekUser(this.username).isOnline()) {
            message = this.username + " is offline.";
        } else {
            MusicPlayer player = library.seekUser(this.username).getPlayer();
            player.updatePlaying(timestamp);
            ISelectable loaded = player.getCurrentlyLoaded();

            if (loaded == null) {
                message = "Please load a source before using the shuffle function.";
            } else if (loaded.getType() != ISelectable.SearchType.PLAYLIST
                && loaded.getType() != ISelectable.SearchType.ALBUM) {
                message = "The loaded source is not a playlist or an album.";
            } else {
                message = player.toggleShuffle(this.seed)
                        ? "Shuffle function activated successfully."
                        : "Shuffle function deactivated successfully.";
            }
        }

        return new ResponseMsg(this, message);
    }
}
