package functionality.money;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ArtistMoney {
    @JsonIgnore // stores how much revenue each (listened) song has made
    private Map<String, Integer> songMap = new HashMap<String, Integer>();
    @JsonIgnore // stores how much revenue each (bought) merch has made
    private Map<String, Integer> merchMap = new HashMap<String, Integer>();

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
}
