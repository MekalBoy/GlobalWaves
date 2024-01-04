package functionality.wrapped;

import data.User;
import data.WrappedData;

public final class FactoryWrapped {
    private FactoryWrapped() {
    }

    /**
     * Creates WrappedData based on the user's type.
     */
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

    private static WrappedData buildWrapped(final WrappedUser userWrap) {
        return new WrappedData.Builder()
                .topArtists(userWrap.getTopArtists())
                .topGenres(userWrap.getTopGenres())
                .topSongs(userWrap.getTopSongs())
                .topAlbums(userWrap.getTopAlbums())
                .topEpisodes(userWrap.getTopEpisodes())
                .wrappedType(User.UserType.USER)
                .build();
    }

    private static WrappedData buildWrapped(final WrappedArtist artistWrap) {
        return new WrappedData.Builder()
                .topAlbums(artistWrap.getTopAlbums())
                .topSongs(artistWrap.getTopSongs())
                .topFans(artistWrap.getTopFans())
                .listeners(artistWrap.getTopFans())
                .wrappedType(User.UserType.ARTIST)
                .build();
    }

    private static WrappedData buildWrapped(final WrappedHost hostWrap) {
        return new WrappedData.Builder()
                .topEpisodes(hostWrap.getTopEpisodes())
                .listeners(hostWrap.getTopFans())
                .wrappedType(User.UserType.HOST)
                .build();
    }
}
