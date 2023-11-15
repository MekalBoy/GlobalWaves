package command;

import data.ISelectable;
import data.Library;
import functionality.MusicPlayer;
import functionality.SearchBar;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandBackward extends Command {

    public CommandBackward() {}

    public CommandBackward(String command, String username, int timestamp) {
        super(command, username, timestamp);
    }

    @Override
    public ResponseMsg processCommand() {
        String message;

        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
        ISelectable selection = player.getCurrentlyLoaded();

        if (selection == null) {
            message = "Please select a source before rewinding.";
        } else if (selection.getType() != SearchBar.SearchType.PODCAST) {
            message = "The loaded source is not a podcast.";
        } else {
            player.Backward(timestamp);
            message = "Rewound successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
