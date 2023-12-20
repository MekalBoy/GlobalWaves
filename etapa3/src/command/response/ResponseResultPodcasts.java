package command.response;

import command.Command;
import data.Podcast;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResponseResultPodcasts extends Response {
    private List<Podcast.PodcastInfo> result;

    public ResponseResultPodcasts(final Command command, final List<Podcast.PodcastInfo> result) {
        super(command);
        this.result = result;
    }
}
