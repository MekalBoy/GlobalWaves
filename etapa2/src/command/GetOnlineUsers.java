package command;

import command.response.ResponseResultString;
import data.Library;
import data.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class GetOnlineUsers extends Command {
    @Override
    public final ResponseResultString processCommand() {
        List<String> result = Library.instance.getUsers().stream()
                .filter(User::isOnline)
                .filter(user -> user.getUserType() == User.UserType.USER)
                .map(User::getUsername).toList();

        return new ResponseResultString(this, result);
    }
}
