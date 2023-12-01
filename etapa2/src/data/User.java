package data;

import fileio.input.UserInput;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;
import functionality.Page;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class User {
    public enum UserType {
        NORMAL,
        ARTIST,
        HOST
    }
    private String username, city;
    private int age;

    private UserType userType = UserType.NORMAL;
    private boolean isOnline = true;

    private MusicPlayer player = new MusicPlayer();
    private Page page = new Page(Page.PageType.HOME, this);

    // Artist only
    private List<Album> albumList;
    private List<Merch> merchList;
    private List<ArtistEvent> eventList;
    // Host only
    private List<Podcast> podcastList;
    private List<Announcement> announcementList;

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
        if (albumList == null) {
            albumList = new ArrayList<Album>();
        }
        albumList.add(album);
    }

    /**
     * Adds a new merch listing to the artist's list.
     */
    public void addMerch(final Merch merch) {
        if (merchList == null) {
            merchList = new ArrayList<Merch>();
        }
        merchList.add(merch);
    }

    /**
     * Adds a new event to the artist's list.
     */
    public void addArtistEvent(final ArtistEvent artistEvent) {
        if (eventList == null) {
            eventList = new ArrayList<ArtistEvent>();
        }
        eventList.add(artistEvent);
    }

    /**
     * Adds a podcast to the host's list.
     */
    public void addPodcast(final Podcast podcast) {
        if (podcastList == null) {
            podcastList = new ArrayList<Podcast>();
        }
        podcastList.add(podcast);
    }

    /**
     * Adds a new announcement to the host's list.
     */
    public void addAnnouncement(final Announcement announcement) {
        if (announcementList == null) {
            announcementList = new ArrayList<Announcement>();
        }
        announcementList.add(announcement);
    }
}
