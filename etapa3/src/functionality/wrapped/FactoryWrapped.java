package functionality.wrapped;

import data.User;
import data.WrappedData;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FactoryWrapped {
    public static WrappedData createWrapped(final User user) {
        switch (user.getUserType()) {
            case USER:
                return buildWrapped((WrappedUser) user.getPlayer().getWrappedStats());
            case ARTIST:
                return buildWrapped((WrappedArtist) user.getPlayer().getWrappedStats());
            case HOST:
                return buildWrapped((WrappedHost) user.getPlayer().getWrappedStats());
            default:
                throw new IllegalArgumentException("Invalid userType");
        }
    }

    public static WrappedData buildWrapped(final WrappedUser userWrap) {
        return new WrappedData.Builder()
                .topArtists(userWrap.getTopArtists())
                .topGenres(userWrap.getTopGenres())
                .topSongs(userWrap.getTopSongs())
                .topAlbums(userWrap.getTopAlbums())
                .topEpisodes(userWrap.getTopEpisodes())
                .build();
    }

    public static WrappedData buildWrapped(final WrappedArtist artistWrap) {
        return new WrappedData.Builder()
                .topAlbums(artistWrap.getTopAlbums())
                .topSongs(artistWrap.getTopSongs())
                .topFans(artistWrap.getTopFans())
                .listeners(artistWrap.getTopFans())
                .build();
    }

    public static WrappedData buildWrapped(final WrappedHost hostWrap) {
        return new WrappedData.Builder()
                .topEpisodes(hostWrap.getTopEpisodes())
                .listeners(hostWrap.getTopFans())
                .build();
    }
}
