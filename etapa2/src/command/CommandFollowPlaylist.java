package command;

import command.response.ResponseMsg;
import data.ISelectable;
import data.Library;
import data.Playlist;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
public class CommandFollowPlaylist extends Command {

    @Override
    public final ResponseMsg processCommand() {
        String message;

        if (!Library.instance.seekUser(this.username).isOnline()) {
            message = this.username + " is offline.";
        } else {
            MusicPlayer player = Library.instance.seekUser(username).getPlayer();
            ISelectable selection = player.getCurrentSelection();

            if (selection == null) {
                message = "Please select a source before following or unfollowing.";
            } else if (selection.getType() != ISelectable.SearchType.PLAYLIST) {
                message = "The selected source is not a playlist.";
            } else if (Objects.equals(((Playlist) selection).getOwner(), username)) {
                message = "You cannot follow or unfollow your own playlist.";
            } else {
                message = "Playlist "
                        + (player.followUnfollow((Playlist) selection) ? "followed" : "unfollowed")
                        + " successfully.";
            }
        }

        return new ResponseMsg(this, message);
    }
}
