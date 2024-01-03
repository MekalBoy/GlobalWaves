package data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class WrappedData {
    // User
    private Map<String, Integer> topArtists, topGenres;
    // User and Artist
    private Map<String, Integer> topSongs, topAlbums;
    // User and Host
    private Map<String, Integer> topEpisodes;
    // Artist
    private List<String> topFans;
    // Artist and Host
    private Integer listeners;

    public static class Builder {
        private Map<String, Integer> topArtists, topGenres, topSongs, topAlbums, topEpisodes;
        private List<String> topFans;
        private Integer listeners;

        public Builder() {
        }

        private Map<String, Integer> sortAndLimit(final Map<String, Integer> unsortedMap) {
            final int mapLimit = 5;

            return unsortedMap.entrySet()
                    .stream()
                    .sorted(Comparator.comparing(Map.Entry<String, Integer>::getKey))
                    .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue, Comparator.reverseOrder()))
                    .limit(mapLimit)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new
                    ));
        }

        public Builder topArtists(final Map<String, Integer> unsortedArtists) {
            this.topArtists = sortAndLimit(unsortedArtists);
            return this;
        }

        public Builder topGenres(final Map<String, Integer> unsortedGenres) {
            this.topGenres = sortAndLimit(unsortedGenres);
            return this;
        }

        public Builder topAlbums(final Map<String, Integer> unsortedAlbums) {
            this.topAlbums = sortAndLimit(unsortedAlbums);
            return this;
        }

        public Builder topSongs(final Map<String, Integer> unsortedSongs) {
            this.topSongs = sortAndLimit(unsortedSongs);
            return this;
        }

        public Builder topEpisodes(final Map<String, Integer> unsortedEpisodes) {
            this.topEpisodes = sortAndLimit(unsortedEpisodes);
            return this;
        }

        public Builder topFans(final Map<String, Integer> topFansMap) {
            this.topFans = sortAndLimit(topFansMap).keySet().stream().toList();
            return this;
        }

        public Builder listeners(final Map<String, Integer> topFansMap) {
            this.listeners = topFansMap.keySet().size();
            return this;
        }

        public WrappedData build() {
            return new WrappedData(this);
        }
    }

    private WrappedData(final Builder builder) {
        this.topArtists = builder.topArtists;
        this.topGenres = builder.topGenres;
        this.topSongs = builder.topSongs;
        this.topAlbums = builder.topAlbums;
        this.topEpisodes = builder.topEpisodes;
        this.topFans = builder.topFans;
        this.listeners = builder.listeners;
    }
}
