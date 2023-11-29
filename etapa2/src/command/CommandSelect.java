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

    @Override
    public final ResponseMsg processCommand() {
        String message;

        if (!Library.instance.seekUser(this.username).isOnline()) {
            message = this.username
                    + " is offline.";
        } else {
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
        }

        return new ResponseMsg(this, message);
    }
}
