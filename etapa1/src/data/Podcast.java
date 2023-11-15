package data;

import fileio.input.EpisodeInput;
import fileio.input.PodcastInput;
import functionality.SearchBar;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Podcast implements ISelectable {
    private String name, owner;
    private List<Episode> episodes;

    public Podcast(String name, String owner, List<Episode> episodes) {
        this.name = name;
        this.owner = owner;
        this.episodes = episodes;
    }

    public Podcast(PodcastInput input) {
        this.name = input.getName();
        this.owner = input.getOwner();
        this.episodes = new ArrayList<Episode>();
        for (EpisodeInput episodeInput : input.getEpisodes()) {
            this.episodes.add(new Episode(episodeInput));
        }
    }

    public Episode getNextAfter(AudioFile file) {
        Episode episode = (Episode) file;
        int index = episodes.indexOf(episode);
        if (index == -1 || index == episodes.size() - 1) return null;
        return episodes.get(index + 1);
    }

    @Override
    public SearchBar.SearchType getType() {
        return SearchBar.SearchType.PODCAST;
    }

    @Override
    public boolean isCollection() {
        return true;
    }
}