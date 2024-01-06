package command;

import command.response.ResponseMsg;
import data.Library;
import data.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdBreak extends Command {
    private int price;
    @Override
    public final ResponseMsg processCommand() {
        String message = null;

        User user = Library.getInstance().seekUser(username);

        if (user == null) {
            message = "The username " + username + " doesn't exist.";
        } else {
            user.getPlayer().updatePlaying(timestamp);

            if (!user.getPlayer().isPlaying()) {
                message = username + " is not playing any music.";
            } else {
                // annoy the user
                user.getPlayer().insertAd(price, timestamp);

                // paying the artists is done inside the insertAd function
                // when the ad is completed
                // MoneyManager.getInstance().payAdverts(user, price);

                message = "Ad inserted successfully.";
            }
        }


        return new ResponseMsg(this, message);
    }
}
