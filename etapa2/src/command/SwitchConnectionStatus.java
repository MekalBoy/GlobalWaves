package command;

import command.response.ResponseMsg;
import data.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SwitchConnectionStatus extends Command {
    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = library.seekUser(this.username);

        if (user == null) {
            message = "The username " + this.username + " doesn't exist.";
        } else if (user.getUserType() != User.UserType.USER) {
            message = this.username + " is not a normal user.";
        } else {
            // Only update the player's status if the user is going into sleep mode (offline)
            // Otherwise just update the lastUpdateTime (user is coming online, nothing is updated)
            boolean updateTime = user.toggleOnline();
            if (updateTime) { // was offline, now online
                user.getPlayer().setLastUpdateTime(this.timestamp);
            } else { // was online, going offline, update everything
                user.getPlayer().updatePlaying(this.timestamp);
            }
            message = this.username + " has changed status successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
