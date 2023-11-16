package command;

import data.Library;
import data.Playlist;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter
public class CommandShowPlaylists extends Command {
    List<Playlist.PlaylistInfo> result;

    public CommandShowPlaylists() {
    }

    public CommandShowPlaylists(final String command, final String username,
                                final int timestamp, final List<Playlist.PlaylistInfo> result) {
        super(command, username, timestamp);
        this.result = result;
    }

    @Override
    public final ResponseResultPlaylists processCommand() {
        result = new ArrayList<Playlist.PlaylistInfo>();

        for (Playlist playlist : Library.instance.getPlaylists()) {
            if (Objects.equals(playlist.getOwner(), this.username)) result.add(new Playlist.PlaylistInfo(playlist));
        }

        return new ResponseResultPlaylists(this, result);
    }
}
