package command.stats;

import command.Command;
import command.response.ResponseResultWrapped;
import data.Library;
import data.User;
import functionality.Wrapped;

public class CommandWrapped extends Command {
    @Override
    public final ResponseResultWrapped processCommand() {
        Wrapped result;

        User user = Library.getInstance().seekUser(username);
        result = user.getPlayer().getWrappedStats();

        return new ResponseResultWrapped(this, result);
    }
}
