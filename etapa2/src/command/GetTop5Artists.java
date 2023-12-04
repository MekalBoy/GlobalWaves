package command;

import command.response.ResponseResultString;
import data.Library;
import data.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter @Setter
public class GetTop5Artists extends Command {
    private final int artistLimit = 5;

    @Override
    public final ResponseResultString processCommand() {
        List<String> result = new ArrayList<String>();

        List<User> sortedArtists = new ArrayList<User>(Library.instance.getUsers().stream()
                .filter(user -> user.getUserType() == User.UserType.ARTIST).toList());
        sortedArtists.sort(Comparator.comparingInt(User::getTotalLikes).reversed());

        for (int i = 0; i < sortedArtists.size() && i < artistLimit; i++) {
            result.add(sortedArtists.get(i).getName());
        }

        return new ResponseResultString(this, result);
    }
}
