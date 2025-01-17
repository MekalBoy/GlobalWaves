package command.removers;

import command.Command;
import command.response.ResponseMsg;
import data.Podcast;
import data.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RemovePodcast extends Command {
    private String name;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = library.seekUser(username);
        for (User normalUser : library.getUsers()) {
            if (normalUser.getUserType() != User.UserType.USER || !normalUser.isOnline()) {
                continue;
            }
            normalUser.getPlayer().updatePlaying(timestamp);
        }

        Podcast podcast = library.seekPodcast(this.name);

        if (user == null) {
            message = "The username " + this.username + " doesn't exist.";
        } else if (user.getUserType() != User.UserType.HOST) {
            message = username + " is not a host.";
        } else if (podcast == null) {
            message = username + " doesn't have a podcast with the given name.";
        } else {
            boolean inUse = false;

            for (User normalUser : library.getUsers()) {
                if (normalUser.getUserType() != User.UserType.USER) {
                    continue;
                }

                if (normalUser.getPlayer().getCurrentlyLoaded() != null
                        && normalUser.getPlayer().getCurrentlyLoaded()
                        .getName().equals(this.name)) {
                    inUse = true;
                    break;
                }
            }

            if (inUse) {
                message = username + " can't delete this podcast.";
            } else {
                library.removePodcast(podcast);
                user.removePodcast(podcast);
                message = username + " deleted the podcast successfully.";
            }
        }

        return new ResponseMsg(this, message);
    }
}
