package command;

import data.Library;
import data.Song;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter @Setter
public class GetTop5Songs extends Command {

    public GetTop5Songs() {}

    public GetTop5Songs(String command, String username, int timestamp) {
        super(command, username, timestamp);
    }

    @Override
    public ResponseResultString processCommand() {
        List<String> result = new ArrayList<String>();

        // TODO: fix

        PriorityQueue<Song> maxHeap = new PriorityQueue<>(Comparator.comparing(Song::getNrLikes).reversed());
        maxHeap.addAll(Library.instance.getSongs());
        for (int i = 0; i < 5; i++) {
            result.add(maxHeap.remove().getName());
        }

        return new ResponseResultString(this.command, this.username, this.timestamp, result);
    }
}
