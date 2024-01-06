package command.response;

import command.Command;
import data.CreatorNotification;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResponseNotifications extends Response {
    private List<CreatorNotification> notifications;

    public ResponseNotifications(final Command command,
                                 final List<CreatorNotification> notifications) {
        super(command);
        this.notifications = notifications;
    }
}
