package functionality.money;

import com.fasterxml.jackson.annotation.JsonIgnore;
import data.Merch;
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
     * Adds a unit's worth of merch to the total.
     */
    public void addMerchRevenue(final Merch merch) {
        merchMap.put(merch.getName(), merchMap.getOrDefault(merch.getName(), (double) 0)
                + ((double) merch.getPrice()));
        merchRevenue += merch.getPrice();
    }

    /**
     * Updates the mostProfitableSong variable.
     */
    private void updateMostProfitable() {
        if (songMap == null || songMap.isEmpty()) {
            return; // or throw an exception, depending on your requirements
        }

        // If revenues are equal, sort alphabetically by song name
        Map.Entry<String, Double> maxEntry = songMap.entrySet().stream().min((entry1, entry2) -> {
                    int revenueComparison = entry2.getValue().compareTo(entry1.getValue());

                    if (revenueComparison == 0) {
                        // If revenues are equal, sort alphabetically by song name
                        return entry1.getKey().compareTo(entry2.getKey());
                    } else {
                        return revenueComparison;
                    }
                })
                .orElse(null);

        mostProfitableSong = maxEntry.getKey();
    }
}
