package command;

import data.Library;
import data.User;
import lombok.Getter;
import lombok.Setter;

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

        // find user, find their latest search results, set the selected thing based on results and provided index
        User user = Library.instance.seekUser(this.username);
        if (user.getSearchResults().isEmpty()) {
            message = "Please conduct a search before making a selection.";
        } else if (user.getSearchResults().size() < this.itemNumber) {
            message = "The selected ID is too high.";
        } else {
            String trackName = user.getSearchResults().get(this.itemNumber - 1).getName();
            user.setCurrentSelection(user.getSearchResults().get(this.itemNumber - 1));
            message = "Successfully selected " + trackName + ".";
        }

        return new ResponseMsg(this.command, this.username, this.timestamp, message);
    }
}
