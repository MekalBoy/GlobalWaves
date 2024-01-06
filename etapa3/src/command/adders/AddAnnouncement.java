package command.adders;

import command.Command;
import command.response.ResponseMsg;
import data.Announcement;
import data.CreatorNotification;
import data.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddAnnouncement extends Command {
    private String name, description;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = library.seekUser(username);

        if (user == null) {
            message = "The username " + this.username + " doesn't exist.";
        } else if (user.getUserType() != User.UserType.HOST) {
            message = this.username + " is not a host.";
        } else if (user.getAnnouncementList().stream()
                .map(Announcement::getName).toList()
                .contains(this.name)) {
            message = this.username + " has already added an announcement with this name.";
        } else {
            user.addAnnouncement(new Announcement(name, description));
            message = this.username + " has successfully added new announcement.";

            // notify subscribers
            user.notifyAllSubs(new CreatorNotification("New Announcement",
                    "New Announcement from " + user.getUsername() + "."));
        }

        return new ResponseMsg(this, message);
    }
}
