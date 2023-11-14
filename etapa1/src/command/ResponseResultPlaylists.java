package command;

import data.Playlist;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResponseResultPlaylists extends Response {
    List<Playlist.PlaylistInfo> result;

    public ResponseResultPlaylists(String command, String user, int timestamp, List<Playlist.PlaylistInfo> result) {
        super(command, user, timestamp);
        this.result = result;
    }

    public ResponseResultPlaylists(Command command, List<Playlist.PlaylistInfo> result) {
        super(command);
        this.result = result;
    }
}
