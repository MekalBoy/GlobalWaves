package command;

import data.ISelectable;
import data.Library;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandShuffle extends Command {
    private int seed;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        if (!Library.instance.seekUser(this.username).isOnline()) {
            message = this.username
                    + " is offline.";
        } else {
            MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
            player.updatePlaying(timestamp);
            ISelectable loaded = player.getCurrentlyLoaded();

            if (loaded == null) {
                message = "Please load a source before using the shuffle function.";
            } else if (loaded.getType() != ISelectable.SearchType.PLAYLIST) {
                message = "The loaded source is not a playlist.";
            } else {
                message = player.toggleShuffle(this.seed)
                        ? "Shuffle function activated successfully."
                        : "Shuffle function deactivated successfully.";
            }
        }

        return new ResponseMsg(this, message);
    }
}
