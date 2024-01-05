package command;

import command.response.ResponseMsg;
import data.Library;
import data.User;
import functionality.money.MoneyManager;
import functionality.money.UserMoney;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BuyPremium extends Command {
    @Override
    public final ResponseMsg processCommand() {
        String message = null;

        User user = Library.getInstance().seekUser(username);

        if (user == null) {
            message = "The username " + username + " doesn't exist.";
        } else if (MoneyManager.getInstance().getUserDatabase()
                .getOrDefault(username, new UserMoney()).isPremium()) {
            message = username + " is already a premium user.";
        } else {
            // do something to toggle premium on

            message = username + " bought the subscription successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
