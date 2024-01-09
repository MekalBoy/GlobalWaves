package data;

import fileio.input.UserInput;
import functionality.MusicPlayer;
import functionality.Page;
import functionality.wrapped.WrappedArtist;
import functionality.wrapped.WrappedHost;
import functionality.wrapped.WrappedUser;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
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

    // Time constraints prevent me from making proper classes
    // for Artist and Host, so this will have to do for now

    // Artist only
    private List<Album> albumList = new ArrayList<Album>();
    private List<Merch> merchList = new ArrayList<Merch>();
    private List<ArtistEvent> eventList = new ArrayList<ArtistEvent>();;
    // Host only
    private List<Podcast> podcastList = new ArrayList<Podcast>();
    private List<Announcement> announcementList = new ArrayList<Announcement>();

    // Users subscribed to this person's notifications
    private List<User> subscriberList = new ArrayList<User>();
    // Unread notifications list
    private List<CreatorNotification> notificationList =
            new LinkedList<CreatorNotification>();

    public User() {
    }

    public User(final String username, final String city, final int age, final String type) {
        this.username = username;
        this.city = city;
        this.age = age;
        this.userType = UserType.valueOf(type.toUpperCase());
        switch (userType) {
            case USER -> player.setWrappedStats(new WrappedUser());
            case ARTIST -> player.setWrappedStats(new WrappedArtist());
            case HOST -> player.setWrappedStats(new WrappedHost());
            default -> throw new IllegalArgumentException("Invalid userType");
        }
        player.setOwner(this);
    }

    public User(final UserInput input) {
        this.username = input.getUsername();
        this.city = input.getCity();
        this.age = input.getAge();
        // hardcoded because all og inputs are users
        this.userType = UserType.USER;
        player.setWrappedStats(new WrappedUser());
        player.setOwner(this);
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
     * Removes the album from the artist's list.
     */
    public void removeAlbum(final Album album) {
        albumList.remove(album);
    }

    /**
     * Returns the total amount of likes of all the artist's albums.
     */
    public int getTotalLikes() {
        int sum = 0;
        for (Album album : albumList) {
            sum += album.getTotalLikes();
        }
        return sum;
    }

    /**
     * Adds a new merch listing to the artist's list.
     */
    public void addMerch(final Merch merch) {
        merchList.add(merch);
    }

    /**
     * Returns a merch by its name.
     * @return Merch object if found; null otherwise
     */
    public Merch seekMerch(final String name) {
        for (Merch merch : merchList) {
            if (merch.getName().equals(name)) {
                return merch;
            }
        }

        return null;
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
     * Adds or removes the user in the creator's subscriber list.
     * @return true if the user is now in; false if the user has been removed
     */
    public boolean subscribeUnsubscribe(final User user) {
        if (subscriberList.contains(user)) {
            subscriberList.remove(user);
            return false;
        } else {
            subscriberList.add(user);
            return true;
        }
    }

    /**
     * Notifies every subscriber of the latest notification.
     */
    public void notifyAllSubs(final CreatorNotification notification) {
        for (User user : subscriberList) {
            user.addNotification(notification);
        }
    }

    /**
     * Adds a notification to a user's notifications list.
     */
    public void addNotification(final CreatorNotification notification) {
        notificationList.add(notification);
    }

    /**
     * Clears the notifications list.
     */
    public void clearNotifications() {
        notificationList.clear();
    }

    /**
     * Erases all traces of the user's existence from the library.
     */
    public void eraseTraces() {
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
}
