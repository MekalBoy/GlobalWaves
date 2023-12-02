package command;

import data.Library;
import data.User;
import data.Podcast;
import data.Episode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class AddPodcast extends Command {
    private String name;
    private List<Episode> episodes;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        Library library = Library.instance;
        User user = library.seekUser(this.username);
        Podcast podcast = library.seekPodcast(this.name);

        if (user == null) {
            message = "The username " + this.username + " doesn't exist.";
        } else if (user.getUserType() != User.UserType.HOST) {
            message = this.username + " is not a host.";
        } else if (podcast != null && podcast.getOwner().equals(this.username)) {
            message = this.username + " has another podcast with the same name.";
        } else if (episodes.size() != episodes.stream().map(Episode::getName).distinct().count()) {
            message = this.username + " has the same episode in this podcast.";
        } else {
            podcast = new Podcast(this.name, this.username, episodes);
            library.addPodcast(podcast);
            user.addPodcast(podcast);
            message = this.username + " has added new podcast successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
