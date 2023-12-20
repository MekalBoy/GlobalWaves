package command;

import command.response.ResponseMsg;
import data.ISelectable;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandForward extends Command {

    @Override
    public final ResponseMsg processCommand() {
        String message;

        if (!library.seekUser(this.username).isOnline()) {
            message = this.username + " is offline.";
        } else {
            MusicPlayer player = library.seekUser(this.username).getPlayer();
            player.updatePlaying(timestamp);
            ISelectable selection = player.getCurrentlyLoaded();

            if (selection == null) {
                message = "Please load a source before attempting to forward.";
            } else if (selection.getType() != ISelectable.SearchType.PODCAST) {
                message = "The loaded source is not a podcast.";
            } else {
                player.forward();
                message = "Skipped forward successfully.";
            }
        }

        return new ResponseMsg(this, message);
    }
}
