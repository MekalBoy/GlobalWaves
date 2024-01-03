package functionality.wrapped;

import data.Episode;
import data.ISelectable;
import data.User;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter @Setter
public class WrappedHost extends Wrapped {
    private Map<String, Integer> topEpisodes = new LinkedHashMap<String, Integer>();
    private Map<String, Integer> topFans = new LinkedHashMap<String, Integer>();

    @Override
    public final void incrementTop(final ISelectable selected, final User user) {
        Episode episode = (Episode) selected;
        incrementMap(episode.getName(), topEpisodes);
        incrementMap(user.getUsername(), topFans);
    }
}
