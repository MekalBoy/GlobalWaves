package command.stats;

import command.Command;
import command.response.ResponseResultString;
import data.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class GetAllUsers extends Command {
    @Override
    public final ResponseResultString processCommand() {
        List<String> result = new java.util.ArrayList<>(library.getUsers().stream()
                .filter(user -> user.getUserType() == User.UserType.USER)
                .map(User::getUsername).toList());

        result.addAll(library.getUsers().stream()
                .filter(user -> user.getUserType() == User.UserType.ARTIST)
                .map(User::getUsername).toList());

        result.addAll(library.getUsers().stream()
                .filter(user -> user.getUserType() == User.UserType.HOST)
                .map(User::getUsername).toList());

        return new ResponseResultString(this, result);
    }
}
