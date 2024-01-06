package command;

import command.response.Response;
import command.response.ResponseMsg;
import command.response.ResponseResultString;
import data.Library;
import data.Merch;
import data.User;
import functionality.money.MoneyManager;
import functionality.money.UserMoney;

import java.util.ArrayList;
import java.util.List;

public class SeeMerch extends Command {
    @Override
    public Response processCommand() {
        String message = null;
        List<String> result = new ArrayList<String>();

        User user = Library.getInstance().seekUser(username);

        if (user == null) {
            message = "The username " + username + " doesn't exist.";
            return new ResponseMsg(this, message);
        }

        MoneyManager.getInstance().getUserDatabase().putIfAbsent(username, new UserMoney());

        result = MoneyManager.getInstance().getUserDatabase().get(username)
                .getBoughtMerch().stream().map(Merch::getName).toList();

        return new ResponseResultString(this, result);
    }
}
