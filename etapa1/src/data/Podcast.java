package data;

import fileio.input.EpisodeInput;
import fileio.input.PodcastInput;

import java.util.ArrayList;
import java.util.List;

public class Podcast {
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
}