package command.adders;

import command.Command;
import command.response.ResponseMsg;
import data.ArtistEvent;
import data.CreatorNotification;
import data.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddEvent extends Command {
    private String name, description, date;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = library.seekUser(this.username);

        final int lowestYear = 1900;
        final int highestYear = 2023;
        final int highestMonth = 12;
        final int highestDay = 31;
        final int highestDayFeb = 28;

        String[] parts = date.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        boolean invalidDate = (year < lowestYear || year > highestYear)
                || (month == 2 && day > highestDayFeb)
                || (day > highestDay) || (month > highestMonth);

        if (user == null) {
            message = "The username " + this.username + " doesn't exist.";
        } else if (user.getUserType() != User.UserType.ARTIST) {
            message = username + " is not an artist.";
        } else if (user.getEventList().stream()
                .map(ArtistEvent::getName).toList()
                .contains(this.name)) {
            message = username + " has another event with the same name.";
        } else if (invalidDate) {
            message = "Event for " + username + " does not have a valid date.";
        } else {
            ArtistEvent event = new ArtistEvent(name, description, date);
            user.addArtistEvent(event);
            message = username + " has added new event successfully.";

            // notify subscribers
            user.notifyAllSubs(new CreatorNotification("New Event",
                    "New Event from " + user.getUsername() + "."));
        }

        return new ResponseMsg(this, message);
    }
}
