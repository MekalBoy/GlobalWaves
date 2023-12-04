package command;

import command.response.ResponseResultPlaylists;
import data.Library;
import data.Playlist;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter
public class CommandShowPlaylists extends Command {
    private List<Playlist.PlaylistInfo> result;

    @Override
    public final ResponseResultPlaylists processCommand() {
        result = new ArrayList<Playlist.PlaylistInfo>();

        for (Playlist playlist : Library.instance.getPlaylists()) {
            if (Objects.equals(playlist.getOwner(), this.username)) {
                result.add(new Playlist.PlaylistInfo(playlist));
            }
        }

        return new ResponseResultPlaylists(this, result);
    }
}
