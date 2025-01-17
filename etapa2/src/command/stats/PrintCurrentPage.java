package command.stats;

import command.Command;
import command.response.ResponseMsg;
import data.User;
import functionality.Page;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PrintCurrentPage extends Command {
    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = library.seekUser(this.username);
        Page page = user.getPage();

        if (!user.isOnline()) {
            message = this.username + " is offline.";
        } else {
            message = page.toString();
        }

        return new ResponseMsg(this, message);
    }
}
