package command;

import data.ISelectable;
import data.Library;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommandSelect extends Command {
    private int itemNumber;

    public CommandSelect() {}

    public CommandSelect(String command, String username, int timestamp, int itemNumber) {
        super(command, username, timestamp);
        this.itemNumber = itemNumber;
    }

    public Response processCommand() {
        String message;

        // find user's player, find their latest search results, set the selected thing based on results and provided index
        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
        List<ISelectable> searchResults = player.getSearchResults();

        if (searchResults == null) {
            message = "Please conduct a search before making a selection.";
        } else if (searchResults.size() < this.itemNumber) {
            message = "The selected ID is too high.";
        } else {
            String trackName = searchResults.get(this.itemNumber - 1).getName();
            player.setCurrentSelection(searchResults.get(this.itemNumber - 1));
            player.setSearchResults(null);
            message = "Successfully selected " + trackName + ".";
        }

        return new ResponseMsg(this, message);
    }
}
