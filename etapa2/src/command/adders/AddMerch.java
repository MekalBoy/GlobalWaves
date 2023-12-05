package command.adders;

import command.Command;
import command.response.ResponseMsg;
import data.Library;
import data.Merch;
import data.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddMerch extends Command {
    private String name, description;
    private int price;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = Library.instance.seekUser(username);

        if (user == null) {
            message = "The username " + this.username + " doesn't exist.";
        } else if (user.getUserType() != User.UserType.ARTIST) {
            message = username + " is not an artist.";
        } else if (user.getMerchList().stream()
                .map(Merch::getName).toList()
                .contains(this.name)) {
            message = username + " has merchandise with the same name.";
        } else if (price < 0) {
            message = "Price for merchandise can not be negative.";
        } else {
            user.addMerch(new Merch(name, price, description));
            message = username + " has added new merchandise successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
