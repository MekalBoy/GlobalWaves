package data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public final class WrappedData {
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

    @JsonIgnore
    private User.UserType wrappedType;

    public static final class Builder {
        private Map<String, Integer> topArtists, topGenres, topSongs, topAlbums, topEpisodes;
        private List<String> topFans;
        private Integer listeners;
        private User.UserType wrappedType;

        public Builder() {
        }

        private Map<String, Integer> sortAndLimit(final Map<String, Integer> unsortedMap) {
            final int mapLimit = 5;

            return unsortedMap.entrySet()
                    .stream()
                    .sorted(Comparator.comparing(Map.Entry<String, Integer>::getKey))
                    .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue,
                            Comparator.reverseOrder()))
                    .limit(mapLimit)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new
                    ));
        }

        /**
         * Sorts the dictionary alphabetically, then (descending) by values
         * and limits the results to 5 before assigning the sorted map.
         */
        public Builder topArtists(final Map<String, Integer> unsortedArtists) {
            this.topArtists = sortAndLimit(unsortedArtists);
            return this;
        }

        /**
         * Sorts the dictionary alphabetically, then (descending) by values
         * and limits the results to 5 before assigning the sorted map.
         */
        public Builder topGenres(final Map<String, Integer> unsortedGenres) {
            this.topGenres = sortAndLimit(unsortedGenres);
            return this;
        }

        /**
         * Sorts the dictionary alphabetically, then (descending) by values
         * and limits the results to 5 before assigning the sorted map.
         */
        public Builder topAlbums(final Map<String, Integer> unsortedAlbums) {
            this.topAlbums = sortAndLimit(unsortedAlbums);
            return this;
        }

        /**
         * Sorts the dictionary alphabetically, then (descending) by values
         * and limits the results to 5 before assigning the sorted map.
         */
        public Builder topSongs(final Map<String, Integer> unsortedSongs) {
            this.topSongs = sortAndLimit(unsortedSongs);
            return this;
        }

        /**
         * Sorts the dictionary alphabetically, then (descending) by values
         * and limits the results to 5 before assigning the sorted map.
         */
        public Builder topEpisodes(final Map<String, Integer> unsortedEpisodes) {
            this.topEpisodes = sortAndLimit(unsortedEpisodes);
            return this;
        }

        /**
         * Sorts the dictionary alphabetically, then (descending) by values
         * and limits the results to 5 before assigning the sorted map.
         */
        public Builder topFans(final Map<String, Integer> topFansMap) {
            this.topFans = sortAndLimit(topFansMap).keySet().stream().toList();
            return this;
        }

        /**
         * Sets the listeners to the number of keys there are.
         */
        public Builder listeners(final Map<String, Integer> topFansMap) {
            this.listeners = topFansMap.keySet().size();
            return this;
        }

        /**
         * Sets the wrapped user type for metadata purposes.
         */
        public Builder wrappedType(final User.UserType type) {
            wrappedType = type;
            return this;
        }

        /**
         * Builds the WrappedData per se after the builder's properly set up.
         */
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
        this.wrappedType = builder.wrappedType;
    }

    /**
     * @return true if all fields are empty; false otherwise
     */
    public boolean noData() {
        return switch (wrappedType) {
            case USER -> topArtists.isEmpty() && topGenres.isEmpty() && topAlbums.isEmpty()
                    && topSongs.isEmpty() && topEpisodes.isEmpty();
            case ARTIST -> topSongs.isEmpty() && topAlbums.isEmpty();
            case HOST -> topEpisodes.isEmpty();
            default -> throw new IllegalArgumentException("Invalid wrappedType");
        };
    }
}
