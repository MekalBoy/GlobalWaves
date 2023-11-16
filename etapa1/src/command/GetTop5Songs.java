package command;

import data.Library;
import data.Song;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter @Setter
public class GetTop5Songs extends Command {

    public GetTop5Songs() {
    }

    public GetTop5Songs(final String command, final String username, final int timestamp) {
        super(command, username, timestamp);
    }

    @Override
    public final ResponseResultString processCommand() {
        List<String> result = new ArrayList<String>();

        List<Song> sortedSongs = new ArrayList<Song>(Library.instance.getSongs());
        sortedSongs.sort(Comparator.comparingInt(Song::getNrLikes).reversed());

        for (int i = 0; i < 5; i++) {
            result.add(sortedSongs.get(i).getName());
        }

        return new ResponseResultString(this.command, this.username, this.timestamp, result);
    }
}
