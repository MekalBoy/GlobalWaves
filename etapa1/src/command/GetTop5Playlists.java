package command;

import data.Library;
import data.Playlist;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Getter @Setter
public class GetTop5Playlists extends Command {

    public GetTop5Playlists() {}

    public GetTop5Playlists(String command, String username, int timestamp) {
        super(command, username, timestamp);
    }

    @Override
    public ResponseResultString processCommand() {
        List<String> result = new ArrayList<String>();

        // TODO: fix

        PriorityQueue<Playlist> maxHeap = new PriorityQueue<>(Comparator.comparing(Playlist::getFollowers));
        maxHeap.addAll(Library.instance.getPlaylists());
        for (int i = 0; i < 5; i++) {
            Playlist playlist = maxHeap.peek();
            assert playlist != null;
            if (playlist != null && playlist.getFollowers() > 0)
                result.add(playlist.getName());
        }

        return new ResponseResultString(this.command, this.username, this.timestamp, result);
    }
}
