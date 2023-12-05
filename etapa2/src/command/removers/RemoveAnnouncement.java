package command.removers;

import command.Command;
import command.response.ResponseMsg;
import data.Announcement;
import data.Library;
import data.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RemoveAnnouncement extends Command {
    private String name;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = Library.instance.seekUser(username);

        if (user == null) {
            message = "The username " + this.username + " doesn't exist.";
        } else if (user.getUserType() != User.UserType.HOST) {
            message = username + " is not a host.";
        } else if (!user.getAnnouncementList().stream()
                .map(Announcement::getName).toList().contains(name)) {
            message = username + " has no announcement with the given name.";
        } else {
            user.removeAnnouncement(this.name);
            message = username + " has successfully deleted the announcement.";
        }

        return new ResponseMsg(this, message);
    }
}
