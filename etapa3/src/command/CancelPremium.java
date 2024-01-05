package command;

import command.response.ResponseMsg;
import data.Library;
import data.User;
import functionality.money.MoneyManager;

public class CancelPremium extends Command {
    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = Library.getInstance().seekUser(username);
        MoneyManager manager = MoneyManager.getInstance();

        if (user == null) {
            message = "The username " + username + " doesn't exist.";
        } else if (!manager.getUserDatabase().containsKey(username)
                || !manager.getUserDatabase().get(username).isPremium()) {
            message = username + " is not a premium user.";
        } else {
            // do something to toggle premium off
            manager.disablePremium(user);

            message = username + " cancelled the subscription successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
