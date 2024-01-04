package command.stats;

import command.Command;
import command.response.ResponseResultWrapped;
import data.Library;
import data.User;
import data.WrappedData;
import functionality.wrapped.FactoryWrapped;

public class CommandWrapped extends Command {
    @Override
    public final ResponseResultWrapped processCommand() {
        WrappedData result = null;
        String message = null;

        User user = Library.getInstance().seekUser(username);
        for (User normalUser : library.getUsers()) {
            if (normalUser.getUserType() != User.UserType.USER || !normalUser.isOnline()) {
                continue;
            }
            normalUser.getPlayer().updatePlaying(timestamp);
        }

        result = FactoryWrapped.createWrapped(user);
        if (result.noData()) {
            result = null;
            message = "No data to show for user " + username + ".";
        }

        return new ResponseResultWrapped(this, result, message);
    }
}
