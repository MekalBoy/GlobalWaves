package command.adders;

import command.Command;
import command.response.ResponseMsg;
import data.Album;
import data.CreatorNotification;
import data.Song;
import data.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter @Setter
public class AddAlbum extends Command {
    private String name;
    private int releaseYear;
    private String description;
    private List<Song> songs;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        User user = library.seekUser(this.username);

        if (user == null) {
            message = "The username " + this.username + " doesn't exist.";
        } else if (user.getUserType() != User.UserType.ARTIST) {
            message = this.username + " is not an artist.";
        } else if (user.getAlbumList().stream().map(Album::getName).toList().contains(this.name)) {
            message = this.username + " has another album with the same name.";
        } else if (songs.size() != songs.stream().map(Song::getName).distinct().count()) {
            message = this.username + " has the same song at least twice in this album.";
        } else {
            Album album = new Album(this.name, this.username, this.description,
                    this.releaseYear, this.songs);
            library.addAlbum(album);
            library.setSongs(
                    new ArrayList<Song>(Stream.concat(
                            library.getSongs().stream(),
                            this.songs.stream()).toList())
            );
            user.addAlbum(album);
            message = this.username + " has added new album successfully.";

            // notify subscribers
            user.notifyAllSubs(new CreatorNotification("New Album",
                    "New Album from " + user.getUsername() + "."));
        }

        return new ResponseMsg(this, message);
    }
}
