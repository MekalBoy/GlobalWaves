package data;

import fileio.input.EpisodeInput;
import fileio.input.PodcastInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Podcast implements ISelectable {
    private String name, owner;
    private List<Episode> episodes;

    public Podcast(final String name, final String owner, final List<Episode> episodes) {
        this.name = name;
        this.owner = owner;
        this.episodes = episodes;
    }

    public Podcast(final PodcastInput input) {
        this.name = input.getName();
        this.owner = input.getOwner();
        this.episodes = new ArrayList<Episode>();
        for (EpisodeInput episodeInput : input.getEpisodes()) {
            this.episodes.add(new Episode(episodeInput));
        }
    }

    @Getter @Setter
    public static class PodcastInfo {
        private String name;
        private List<String> episodes;

        public PodcastInfo(final Podcast podcast) {
            this.name = podcast.name;
            this.episodes = podcast.getEpisodes().stream().map(Episode::getName).toList();
        }
    }

    @Override
    public final Episode getNextAfter(final AudioFile file) {
        Episode episode = (Episode) file;
        int index = episodes.indexOf(episode);
        if (index == -1 || index == episodes.size() - 1) {
            return null;
        }
        return episodes.get(index + 1);
    }

    @Override
    public final Episode getPrevBefore(final AudioFile file) {
        Episode episode = (Episode) file;
        int index = episodes.indexOf(episode);
        if (index == -1 || index == 0) {
            return null;
        }
        return episodes.get(index - 1);
    }

    @Override
    public final SearchType getType() {
        return ISelectable.SearchType.PODCAST;
    }

    @Override
    public final boolean isCollection() {
        return true;
    }

    @Override
    public final String toString() {
        return name + ":\n\t" + episodes.stream().map(Episode::toString).toList() + "\n";
    }
}
