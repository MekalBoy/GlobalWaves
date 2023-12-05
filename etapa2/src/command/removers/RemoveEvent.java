package command.removers;

import command.Command;
import command.response.ResponseMsg;
import data.ArtistEvent;
import data.Library;
import data.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RemoveEvent extends Command {
    private String name;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = Library.instance.seekUser(username);

        if (user == null) {
            message = "The username " + this.username + " doesn't exist.";
        } else if (user.getUserType() != User.UserType.ARTIST) {
            message = username + " is not an artist.";
        } else if (!user.getEventList().stream()
                .map(ArtistEvent::getName).toList().contains(name)) {
            message = username + " doesn't have an event with the given name.";
        } else {
            user.removeArtistEvent(this.name);
            message = username + " deleted the event successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
