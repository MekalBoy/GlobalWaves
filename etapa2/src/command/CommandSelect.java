package command;

import command.response.ResponseMsg;
import data.ISelectable;
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

        User user = library.seekUser(this.username);

        if (!user.isOnline()) {
            message = this.username + " is offline.";
        } else {
            MusicPlayer player = user.getPlayer();
            List<ISelectable> searchResults = player.getSearchResults();

            if (searchResults == null) {
                message = "Please conduct a search before making a selection.";
            } else if (searchResults.size() < this.itemNumber) {
                message = "The selected ID is too high.";
            } else {
                ISelectable selected = searchResults.get(this.itemNumber - 1);
                message = "Successfully selected ";

                if (selected.getType() == ISelectable.SearchType.ARTIST) {
                    message += selected.getName() + "'s page.";
                    user.setPage(new Page(Page.PageType.ARTIST,
                            library.seekUser(selected.getName())));
                } else if (selected.getType() == ISelectable.SearchType.HOST) {
                    message += selected.getName() + "'s page.";
                    user.setPage(new Page(Page.PageType.HOST,
                            library.seekUser(selected.getName())));
                } else {
                    String trackName = selected.getName();
                    message += trackName + ".";
                    player.setCurrentSelection(searchResults.get(this.itemNumber - 1));
                }

                player.setSearchResults(null);
            }
        }

        return new ResponseMsg(this, message);
    }
}
