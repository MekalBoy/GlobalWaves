package command;

import data.ISelectable;
import data.Library;
import functionality.MusicPlayer;
import functionality.SearchBar;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandRepeat extends Command {
    @Override
    public ResponseMsg processCommand() {
        String message = "Repeat mode changed to ";

        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
        ISelectable currentlyLoaded = player.getCurrentlyLoaded();

        if (currentlyLoaded == null) {
            message = "Please load a source before setting the repeat status.";
        } else {
            MusicPlayer.RepeatType newType = player.switchRepeat(this.timestamp);

            message += switch (newType) {
                case NO -> "no repeat.";
                case ALL -> currentlyLoaded.getType() == SearchBar.SearchType.PLAYLIST ?
                        "repeat all." : "repeat once.";
                case CURRENT -> currentlyLoaded.getType() == SearchBar.SearchType.PLAYLIST ?
                        "repeat current song." : "repeat infinite.";
            };
        }

        return new ResponseMsg(this, message);
    }
}
