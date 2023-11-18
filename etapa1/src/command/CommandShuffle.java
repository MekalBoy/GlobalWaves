package command;

import data.ISelectable;
import data.Library;
import functionality.MusicPlayer;
import functionality.SearchBar;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandShuffle extends Command {
    private int seed;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
        player.updatePlaying(timestamp);
        ISelectable loaded = player.getCurrentlyLoaded();

        if (loaded == null) {
            message = "Please load a source before using the shuffle function.";
        } else if (loaded.getType() != SearchBar.SearchType.PLAYLIST) {
            message = "The loaded source is not a playlist.";
        } else {
            message = player.toggleShuffle(this.seed)
                    ? "Shuffle function activated successfully."
                    : "Shuffle function deactivated successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
