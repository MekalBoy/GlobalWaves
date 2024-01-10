package command.unimplemented;

import command.Command;
import command.response.ResponseMsg;
import data.Library;
import data.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateRecommendations extends Command {
    private String recommendationType;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = Library.getInstance().seekUser(username);

        if (user == null) {
            message = "The username " + username + " doesn't exist.";
        } else if (user.getUserType() != User.UserType.USER) {
            message = username + " is not a normal user.";
        } else {
            // verify if there are no new recommendations
            // otherwise update recommendations with new stuff

            message = "The recommendations for user "
                    + username
                    + "have been updated successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
