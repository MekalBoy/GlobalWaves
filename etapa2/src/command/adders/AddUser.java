package command.adders;

import command.Command;
import command.response.ResponseMsg;
import data.Library;
import data.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddUser extends Command {
    private String type;
    private int age;
    private String city;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = Library.instance.seekUser(this.username);
        if (user != null) {
            message = "The username "
                    + this.username
                    + " is already taken.";
        } else {
            user = new User(this.username, this.city, this.age, this.type);
            Library.instance.addUser(user);

            message = "The username "
                    + this.username
                    + " has been added successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
