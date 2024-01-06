package command.stats;

import command.Command;
import command.response.ResponseNotifications;
import data.CreatorNotification;
import data.Library;
import data.User;

import java.util.ArrayList;
import java.util.List;

public class GetNotifications extends Command {
    @Override
    public final ResponseNotifications processCommand() {
        List<CreatorNotification> notifications = new ArrayList<CreatorNotification>();

        User user = Library.getInstance().seekUser(username);

        notifications = new ArrayList<CreatorNotification>(user.getNotificationList());
        user.clearNotifications();

        return new ResponseNotifications(this, notifications);
    }
}
