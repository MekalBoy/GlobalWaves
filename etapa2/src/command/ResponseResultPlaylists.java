package command;

import data.Playlist;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResponseResultPlaylists extends Response {
    private List<Playlist.PlaylistInfo> result;

    public ResponseResultPlaylists(final String command, final String user, final int timestamp,
                                   final List<Playlist.PlaylistInfo> result) {
        super(command, user, timestamp);
        this.result = result;
    }

    public ResponseResultPlaylists(final Command command,
                                   final List<Playlist.PlaylistInfo> result) {
        super(command);
        this.result = result;
    }
}
