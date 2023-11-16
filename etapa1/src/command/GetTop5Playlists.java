package command;

import data.Library;
import data.Playlist;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter @Setter
public class GetTop5Playlists extends Command {
    private final int playlistLimit = 5;

    public GetTop5Playlists() {
    }

    public GetTop5Playlists(final String command, final String username, final int timestamp) {
        super(command, username, timestamp);
    }

    @Override
    public final ResponseResultString processCommand() {
        List<String> result = new ArrayList<String>();

        List<Playlist> sortedPlaylists = new ArrayList<Playlist>(Library.instance.getPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers).reversed());

        for (int i = 0; i < sortedPlaylists.size() && i < playlistLimit; i++) {
            result.add(sortedPlaylists.get(i).getName());
        }

        return new ResponseResultString(this.command, this.username, this.timestamp, result);
    }
}
