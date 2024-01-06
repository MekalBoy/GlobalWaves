package command;

import command.response.ResponseMsg;
import data.Library;
import data.User;
import functionality.Page;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandSubscribe extends Command {
    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = Library.getInstance().seekUser(username);

        if (user == null) {
            message = "The username " + username + " doesn't exist.";
        } else {
            Page currentPage = user.getPage();

            if (currentPage.getType() != Page.PageType.ARTIST
                    && currentPage.getType() != Page.PageType.HOST) {
                message = "To subscribe you need to be on the page of an artist or host.";
            } else {
                User creator = currentPage.getCreator();
                boolean hasSubscribedNow = creator.subscribeUnsubscribe(user);

                message = username;
                message += hasSubscribedNow
                        ? " subscribed to "
                        : " unsubscribed from ";
                message += creator.getUsername() + " successfully.";
            }
        }

        return new ResponseMsg(this, message);
    }
}
