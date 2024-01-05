package functionality.money;

import com.fasterxml.jackson.annotation.JsonIgnore;
import data.Song;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ArtistMoney {
    @JsonIgnore // stores how much revenue each (listened) song has made
    private Map<String, Double> songMap = new HashMap<String, Double>();
    @JsonIgnore // stores how much revenue each (bought) merch has made
    private Map<String, Double> merchMap = new HashMap<String, Double>();

    // Data that shows in the endProgram output
    private double songRevenue = 0;
    private double merchRevenue = 0;
    private int ranking = 1;
    private String mostProfitableSong = "N/A";

    public ArtistMoney() {
    }

    public ArtistMoney(final int rank) {
        ranking = rank;
    }

    /**
     * Adds the provided revenue to this song's total.
     */
    public void addSongRevenue(final Song song, final double revenue) {
        songMap.put(song.getName(), songMap.getOrDefault(song.getName(), (double) 0) + revenue);
        songRevenue += revenue;

        updateMostProfitable();
    }

    /**
     * Updates the mostProfitableSong variable.
     */
    private void updateMostProfitable() {
        if (songMap == null || songMap.isEmpty()) {
            return; // or throw an exception, depending on your requirements
        }

        Map.Entry<String, Double> maxEntry = null;
        for (Map.Entry<String, Double> entry : songMap.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }

        mostProfitableSong = maxEntry.getKey();
    }
}
