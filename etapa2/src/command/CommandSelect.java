package command;

import data.ISelectable;
import data.Library;
import data.User;
import functionality.MusicPlayer;
import functionality.Page;
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

        User user = Library.instance.seekUser(this.username);

        if (!user.isOnline()) {
            message = this.username
                    + " is offline.";
        } else {
            MusicPlayer player = user.getPlayer();
            List<ISelectable> searchResults = player.getSearchResults();

            if (searchResults == null) {
                message = "Please conduct a search before making a selection.";
            } else if (searchResults.size() < this.itemNumber) {
                message = "The selected ID is too high.";
            } else {
                ISelectable selected = searchResults.get(this.itemNumber - 1);
                if (selected.getType() == ISelectable.SearchType.ARTIST) {
                    player.setSearchResults(null);
                    message = "Successfully selected " + selected.getName() + "'s page.";
                    user.setPage(new Page(Page.PageType.ARTIST,
                            Library.instance.seekUser(selected.getName())));
                } else if (selected.getType() == ISelectable.SearchType.HOST) {
                    player.setSearchResults(null);
                    message = "Successfully selected " + selected.getName() + "'s page.";
                    user.setPage(new Page(Page.PageType.HOST,
                            Library.instance.seekUser(selected.getName())));
                } else {
                    String trackName = selected.getName();
                    player.setCurrentSelection(searchResults.get(this.itemNumber - 1));
                    player.setSearchResults(null);
                    message = "Successfully selected " + trackName + ".";
                }
            }
        }

        return new ResponseMsg(this, message);
    }
}
