package functionality;

import com.fasterxml.jackson.annotation.JsonIgnore;
import data.ISelectable;
import data.Song;
import data.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class WrappedArtist extends Wrapped {
    private Map<String, Integer> topAlbums = new HashMap<String, Integer>();
    private Map<String, Integer> topSongs = new HashMap<String, Integer>();
    @JsonIgnore
    private Map<String, Integer> topFansMap = new HashMap<String, Integer>();
    @JsonIgnore // the only thing returned should be the list's size
    private List<User> listenersList = new ArrayList<User>();

    @Override
    public final void incrementTop(ISelectable selected, User user) {
        Song song = (Song) selected;
        incrementMap(song.getName(), topSongs);
        incrementMap(song.getAlbum(), topAlbums);
        incrementMap(user.getName(), topFansMap);
        if (!listenersList.contains(user)) {
            // because this stores users, it should probably be addressed in DeleteUser?
            listenersList.add(user);
        }
    }
}
