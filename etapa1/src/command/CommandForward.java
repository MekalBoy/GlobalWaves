package command;

import data.ISelectable;
import data.Library;
import functionality.MusicPlayer;
import functionality.SearchBar;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandForward extends Command {

    @Override
    public final ResponseMsg processCommand() {
        String message;

        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
        ISelectable selection = player.getCurrentlyLoaded();

        if (selection == null) {
            message = "Please load a source before attempting to forward.";
        } else if (selection.getType() != SearchBar.SearchType.PODCAST) {
            message = "The loaded source is not a podcast.";
        } else {
            player.forward(timestamp);
            message = "Skipped forward successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
