package functionality.money;

import data.Song;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter @Setter
public final class UserMoney {
    private boolean isPremium = false;
    // LinkedList allows duplicates
    private List<Song> premiumSongs = new LinkedList<Song>();

    /**
     * Toggles premium status on and off.
     * @return Whatever the status is now.
     */
    public boolean togglePremium() {
        isPremium = !isPremium;
        return isPremium;
    }

    /**
     * Adds the song to the list of listened songs while premium.
     * @param song Song object to add
     */
    public void addToPremiumSongs(final Song song) {
        premiumSongs.add(song);
    }

    /**
     * Clears the premium songs list.
     */
    public void clearPremiumSongs() {
        premiumSongs.clear();
    }
}
