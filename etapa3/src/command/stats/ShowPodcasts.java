package command.stats;

import command.Command;
import command.response.ResponseResultPodcasts;
import data.Podcast;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ShowPodcasts extends Command {
    // unused but the object mapper screams without it
    private int playlistId; // dummy playlistId

    @Override
    public final ResponseResultPodcasts processCommand() {
        List<Podcast.PodcastInfo> result = library.getPodcasts().stream()
                .filter(podcast -> podcast.getOwner().equals(this.username))
                .map(Podcast.PodcastInfo::new).toList();

        return new ResponseResultPodcasts(this, result);
    }
}
