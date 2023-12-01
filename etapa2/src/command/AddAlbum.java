package command;

import data.Album;
import data.Library;
import data.Song;
import data.User;
import lombok.Getter;
import lombok.Setter;

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

        User user = Library.instance.seekUser(this.username);
        Album album = Library.instance.seekAlbum(this.name);

        if (user == null) {
            message = "The username " + this.username + " doesn't exist.";
        } else if (user.getUserType() != User.UserType.ARTIST) {
            message = this.username + " is not an artist.";
        } else if (album != null && album.getOwner().equals(this.username)) {
            message = this.username + " has another album with the same name.";
        } else if (songs.size() != songs.stream().map(Song::getName).distinct().count()) {
            message = this.username + " has the same song at least twice in this album.";
        } else {
            album = new Album(this.name, this.username, this.description,
                    this.releaseYear, this.songs);
            Library.instance.addAlbum(album);
            Library.instance.setSongs(
                    Stream.concat(
                            Library.instance.getSongs().stream(),
                            this.songs.stream()).toList()
            );
            user.addAlbum(album);
            message = this.username + " has added new album successfully.";
        }

        return new ResponseMsg(this, message);
    }
}
