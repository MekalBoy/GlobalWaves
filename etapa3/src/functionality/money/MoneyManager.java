package functionality.money;

import com.fasterxml.jackson.annotation.JsonIgnore;
import data.Library;
import data.Song;
import data.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public final class MoneyManager {
    private Map<String, ArtistMoney> artistDatabase = new HashMap<String, ArtistMoney>();
    @JsonIgnore
    private Map<String, UserMoney> userDatabase = new HashMap<String, UserMoney>();

    @JsonIgnore
    private static MoneyManager instance;

    private MoneyManager() {
    }

    /**
     * @return Current singleton instance.
     */
    public static MoneyManager getInstance() {
        if (instance == null) {
            instance = new MoneyManager();
        }
        return instance;
    }

    /**
     * Creates a new instance of the MoneyManager singleton.
     */
    public static void resetMonetization() {
        instance = new MoneyManager();
    }

    /**
     * Adds the song's artist to the monetization database.
     * @param song The song which has been listened to
     */
    public void tryAddArtist(final Song song) {
        artistDatabase.putIfAbsent(song.getArtist(), new ArtistMoney());

        // Update rankings alphabetically
        List<Map.Entry<String, ArtistMoney>> sortedArtists = artistDatabase.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .toList();

        int newRanking = 1;
        for (Map.Entry<String, ArtistMoney> entry : sortedArtists) {
            entry.getValue().setRanking(newRanking++);
        }
    }

    /**
     * Adds the song to the user's data. If the user is premium,
     * the song is added to the premium list. If not, it's discarded.
     * @param song Song to add
     * @param user User affected
     */
    public void tryAddUser(final Song song, final User user) {
        userDatabase.putIfAbsent(user.getUsername(), new UserMoney());

        UserMoney userMoney = userDatabase.get(user.getUsername());

        // Return if the user is not premium, no need to add?
        if (!userMoney.isPremium()) {
            return;
        }

        userMoney.addToPremiumSongs(song);
    }

    /**
     * Enables premium for the provided user.
     */
    public void enablePremium(final User user) {
        userDatabase.putIfAbsent(user.getUsername(), new UserMoney());

        userDatabase.get(user.getUsername()).setPremium(true);
    }

    /**
     * Disables premium for the provided user and splits the bounty
     * between all songs' artists tracked up to cancellation.
     */
    public void disablePremium(final User user) {
        UserMoney userMoney = userDatabase.get(user.getUsername());

        // Not premium, so nothing to disable.
        if (userMoney == null || !userMoney.isPremium()) {
            return;
        }

        final double premiumBounty = 1000000;

        double totalSongs = userMoney.getPremiumSongs().size();
        double songValue = premiumBounty / totalSongs;

        for (Song song : userMoney.getPremiumSongs()) {
            artistDatabase.get(song.getArtist()).addSongRevenue(song, songValue);
        }

        userMoney.clearPremiumSongs();
        userMoney.setPremium(false);
    }

    /**
     * Cleanup premiums and prepare revenues for endProgram.
     */
    public void cleanupMoney() {
        disableAllPremiums();
        roundRevenues();
        updateRankings();
    }

    /**
     * Cancels all active subscriptions.
     */
    private void disableAllPremiums() {
        for (User user : Library.getInstance().getUsers()) {
            disablePremium(user);
        }
    }

    /**
     * Rounds out every songRevenue.
     */
    private void roundRevenues() {
        final double roundHundred = 100.0;
        for (ArtistMoney artistMoney : artistDatabase.values()) {
            double roundedRevenue = artistMoney.getSongRevenue();
            roundedRevenue = Math.round(roundedRevenue * roundHundred) / roundHundred;
            artistMoney.setSongRevenue(roundedRevenue);
        }
    }

    /**
     * Update the artist rankings based on songRevenue.
     */
    private void updateRankings() {
        List<Map.Entry<String, ArtistMoney>> sortedArtists = artistDatabase.entrySet().stream()
                .sorted((entry1, entry2) -> {
                    int songRevenueComparison = Double.compare(
                            entry2.getValue().getSongRevenue(),
                            entry1.getValue().getSongRevenue()
                    );

                    if (songRevenueComparison == 0) {
                        // If song revenues are equal, sort alphabetically by artist name
                        return entry1.getKey().compareTo(entry2.getKey());
                    } else {
                        return songRevenueComparison;
                    }
                })
                .toList();

        int newRanking = 1;
        for (Map.Entry<String, ArtistMoney> entry : sortedArtists) {
            entry.getValue().setRanking(newRanking++);
        }
    }
}
