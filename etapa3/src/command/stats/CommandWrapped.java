package command.stats;

import command.Command;
import command.response.ResponseResultWrapped;
import data.Library;
import data.User;
import data.WrappedData;
import functionality.wrapped.FactoryWrapped;
import functionality.wrapped.Wrapped;
import org.apache.commons.collections.Factory;

public class CommandWrapped extends Command {
    @Override
    public final ResponseResultWrapped processCommand() {
        WrappedData result;

        User user = Library.getInstance().seekUser(username);
        if (library.seekUser(this.username).isOnline()) {
            user.getPlayer().updatePlaying(timestamp);
        }
        result = FactoryWrapped.createWrapped(user);

        return new ResponseResultWrapped(this, result);
    }
}
