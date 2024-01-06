package functionality.money;

import data.Merch;
import data.Song;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter @Setter
public final class UserMoney {
    private boolean isPremium = false;
    // LinkedList allows duplicates
    private List<Song> premiumSongs = new LinkedList<Song>();
    private List<Song> freeSongs = new LinkedList<Song>();
    private List<Merch> boughtMerch = new ArrayList<Merch>();

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

    /**
     * Adds the song to the list of listened songs while free.
     * @param song Song object to add
     */
    public void addToFreeSongs(final Song song) {
        freeSongs.add(song);
    }

    /**
     * Clears the free songs list.
     */
    public void clearFreeSongs() {
        freeSongs.clear();
    }

    /**
     * Adds the merch to the list of bought stuff.
     */
    public void addToMerchList(final Merch merch) {
        boughtMerch.add(merch);
    }
}
