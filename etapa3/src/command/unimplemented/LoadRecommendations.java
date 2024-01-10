package command.unimplemented;

import command.Command;
import command.response.ResponseMsg;
import data.Library;
import data.User;

public class LoadRecommendations extends Command {
    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = Library.getInstance().seekUser(username);

        if (!user.isOnline()) {
            message = this.username + " is offline.";
        } else {
            // verify if there are any recommendations
            // otherwise load recommended stuff

            message = "Playback loaded successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
