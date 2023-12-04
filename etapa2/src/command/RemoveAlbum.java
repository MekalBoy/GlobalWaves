package command;

import command.response.ResponseMsg;
import data.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class RemoveAlbum extends Command {
    private String name;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = Library.instance.seekUser(username);
        for (User normalUser : Library.instance.getUsers()) {
            if (normalUser.getUserType() != User.UserType.USER || !normalUser.isOnline()) {
                continue;
            }
            normalUser.getPlayer().updatePlaying(timestamp);
        }

        Album album = Library.instance.seekAlbum(this.name);

        if (user == null) {
            message = "The username " + this.username + " doesn't exist.";
        } else if (user.getUserType() != User.UserType.ARTIST) {
            message = username + " is not an artist.";
        } else if (album == null) {
            message = username + " doesn't have an album with the given name.";
        } else {
            boolean inUse = false;

            for (User normalUser : Library.instance.getUsers()) {
                if (normalUser.getUserType() != User.UserType.USER) {
                    continue;
                }

                if (normalUser.getPlayer().getCurrentlyLoaded() != null
                        && normalUser.getPlayer().getCurrentlyLoaded()
                        .getName().equals(this.name)) {
                    inUse = true;
                    break;
                }

                if (normalUser.getPlayer().getCurrentlyLoaded() != null
                        && normalUser.getPlayer().getCurrentlyLoaded()
                        .getType() == ISelectable.SearchType.PLAYLIST) {
                    Playlist playlist = (Playlist) normalUser.getPlayer().getCurrentlyLoaded();
                    List<Song> songsCopy = new ArrayList<Song>(playlist.getSongList());
                    songsCopy.retainAll(album.getSongList());
                    if (!songsCopy.isEmpty()) {
                        inUse = true;
                        break;
                    }
                }
            }

            if (inUse) {
                message = username + " can't delete this album.";
            } else {
                Library.instance.removeAlbum(album);
                user.removeAlbum(album);
                message = username + " deleted the album successfully.";
            }
        }
        return new ResponseMsg(this, message);
    }
}
