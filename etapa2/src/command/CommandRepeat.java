package command;

import command.response.ResponseMsg;
import data.ISelectable;
import data.Library;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandRepeat extends Command {
    @Override
    public final ResponseMsg processCommand() {
        String message = "Repeat mode changed to ";

        if (!Library.instance.seekUser(this.username).isOnline()) {
            message = this.username + " is offline.";
        } else {
            MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
            player.updatePlaying(timestamp);

            ISelectable currentlyLoaded = player.getCurrentlyLoaded();

            if (currentlyLoaded == null) {
                message = "Please load a source before setting the repeat status.";
            } else {
                MusicPlayer.RepeatType newType = player.switchRepeat();

                message += switch (newType) {
                    case NO -> "no repeat.";
                    case ALL -> currentlyLoaded.getType() == ISelectable.SearchType.PLAYLIST
                            ? "repeat all."
                            : "repeat once.";
                    case CURRENT -> currentlyLoaded.getType() == ISelectable.SearchType.PLAYLIST
                            ? "repeat current song."
                            : "repeat infinite.";
                    default -> throw new IllegalArgumentException("Invalid repeatType");
                };
            }
        }

        return new ResponseMsg(this, message);
    }
}
