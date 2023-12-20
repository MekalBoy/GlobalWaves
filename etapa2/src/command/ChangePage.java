package command;

import command.response.ResponseMsg;
import data.User;
import functionality.Page;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChangePage extends Command {
    private String nextPage;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = library.seekUser(username);

        if (!user.isOnline()) {
            message = username + " is offline.";
        } else if (!nextPage.equals("Home") && !nextPage.contains("LikedContent")) {
            message = username + " is trying to access a non-existent page.";
        } else {
            user.setPage(new Page(Page.PageType.valueOf(nextPage.toUpperCase()), user));
            message = username + " accessed " + nextPage + " successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
