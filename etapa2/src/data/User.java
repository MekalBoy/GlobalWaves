package data;

import fileio.input.UserInput;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;
import functionality.Page;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class User implements ISelectable {
    public enum UserType {
        USER,
        ARTIST,
        HOST
    }

    private String username, city;
    private int age;

    private UserType userType = UserType.USER;
    private boolean isOnline = true;

    private MusicPlayer player = new MusicPlayer();
    private Page page = new Page(Page.PageType.HOME, this);

    // Artist only
    private List<Album> albumList = new ArrayList<Album>();
    private List<Merch> merchList = new ArrayList<Merch>();
    private List<ArtistEvent> eventList = new ArrayList<ArtistEvent>();;
    // Host only
    private List<Podcast> podcastList = new ArrayList<Podcast>();
    private List<Announcement> announcementList = new ArrayList<Announcement>();

    public User() {
    }

    public User(final String username, final String city, final int age, final String type) {
        this.username = username;
        this.city = city;
        this.age = age;
        this.userType = UserType.valueOf(type.toUpperCase());
    }

    public User(final UserInput input) {
        this.username = input.getUsername();
        this.city = input.getCity();
        this.age = input.getAge();
    }

    /**
     * Switches the user's connection status.
     * @return true if user is now online; false if now offline
     */
    public boolean toggleOnline() {
        isOnline = !isOnline;
        return isOnline;
    }

    /**
     * Adds an album to the artist's list.
     */
    public void addAlbum(final Album album) {
        albumList.add(album);
    }

    /**
     * Adds a new merch listing to the artist's list.
     */
    public void addMerch(final Merch merch) {
        merchList.add(merch);
    }

    /**
     * Adds a new event to the artist's list.
     */
    public void addArtistEvent(final ArtistEvent artistEvent) {
        eventList.add(artistEvent);
    }

    /**
     * Removes an event from the artist's list.
     * @param eventName Name of the event to remove
     */
    public void removeArtistEvent(final String eventName) {
        eventList.removeIf(event -> event.getName().equals(eventName));
    }

    /**
     * Adds a podcast to the host's list.
     */
    public void addPodcast(final Podcast podcast) {
        podcastList.add(podcast);
    }

    /**
     * Removes the podcast from the host's list.
     */
    public void removePodcast(final Podcast podcast) {
        podcastList.remove(podcast);
    }

    /**
     * Adds a new announcement to the host's list.
     */
    public void addAnnouncement(final Announcement announcement) {
        announcementList.add(announcement);
    }

    /**
     * Removes the announcement from the host's list.
     */
    public void removeAnnouncement(final String announcementName) {
        announcementList.removeIf(announcement
                -> announcement.getName().equals(announcementName));
    }

    /**
     * Erases all traces of the user's existence from the library.
     */
    public void eraseTraces() {
        switch (userType) {
            case ARTIST:
                // THIS IS HORRID, PLEASE LOOK INTO FIXING THIS
                // or at least optimizing it somewhat
                for (Album album : albumList) {
                    for (Song song : album.getSongList()) {
                        for (User normalUser : Library.instance.getUsers()) {
                            if (normalUser.getUserType() != User.UserType.USER) {
                                continue;
                            }
                            MusicPlayer normalPlayer = normalUser.getPlayer();
                            if (normalPlayer.getLikedSongs().contains(song)) {
                                normalPlayer.likeUnlike(song);
                            }
                        }
                    }
                }
                break;
            case HOST:
                break;
            case USER:
            default:
                break;
        }
        player.eraseTraces();
    }

    @Override
    public final SearchType getType() {
        if (userType == UserType.ARTIST) {
            return SearchType.ARTIST;
        } else {
            return SearchType.HOST;
        }
    }

    @Override
    public final String getName() {
        return this.getUsername();
    }

    @Override
    public final boolean isCollection() {
        return false;
    }

    @Override
    public final AudioFile getNextAfter(final AudioFile file) {
        return null;
    }

    @Override
    public final AudioFile getPrevBefore(final AudioFile file) {
        return null;
    }
}
