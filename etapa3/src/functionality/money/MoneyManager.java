package functionality.money;

import com.fasterxml.jackson.annotation.JsonIgnore;
import data.Song;
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
}
