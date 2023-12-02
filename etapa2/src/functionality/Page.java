package functionality;

import data.*;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter @Setter
public class Page {
    public enum PageType {
        HOME,
        LIKED,
        ARTIST,
        HOST
    }

    private PageType type;
    private User creator;

    public Page() {
    }

    public Page(final PageType type, final User creator) {
        this.type = type;
        this.creator = creator;
    }

    @Override
    public final String toString() {
        return switch (this.type) {
            case HOME -> "Liked songs:\n\t["
                    + creator.getPlayer().getLikedSongs().stream()
                    .map(Song::getName)
                    .collect(Collectors.joining(","))
                    + "]\n\nFollowed playlists:\n\t["
                    + creator.getPlayer().getFollowedPlaylists().stream()
                    .map(Playlist::getName)
                    .collect(Collectors.joining(","))
                    + "]";
            case LIKED -> "Liked Songs:\n\t["
                    + creator.getPlayer().getLikedSongs().stream()
                    .map(song -> song.getName() + " - " + song.getArtist())
                    .collect(Collectors.joining(","))
                    + "]\n\nFollowed Playlists:\n\t["
                    + creator.getPlayer().getFollowedPlaylists().stream()
                    .map(playlist -> playlist.getName() + " - " + playlist.getOwner())
                    .collect(Collectors.joining(","))
                    + "]";
            case ARTIST -> "Albums:\n\t["
                    + creator.getAlbumList().stream().map(Album::getName)
                    .collect(Collectors.joining(", "))
                    + "]\n\nMerch:\n\t["
                    + creator.getMerchList().stream().map(Merch::toString)
                    .collect(Collectors.joining(", "))
                    + "]\n\nEvents:\n\t["
                    + creator.getEventList().stream().map(ArtistEvent::toString)
                    .collect(Collectors.joining(", "))
                    + "]";
            case HOST -> "Podcasts:\n\t["
                    + creator.getPodcastList().stream().map(Podcast::toString)
                    .collect(Collectors.joining(", "))
                    + "]\n\tAnnouncements:\n\t["
                    + creator.getAnnouncementList().stream().map(Announcement::toString)
                    .collect(Collectors.joining(", "))
                    + "]";
        };
    }
}
