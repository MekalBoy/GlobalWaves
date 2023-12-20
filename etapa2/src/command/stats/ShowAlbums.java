package command.stats;

import command.Command;
import command.response.ResponseResultAlbums;
import data.Album;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ShowAlbums extends Command {
    @Override
    public final ResponseResultAlbums processCommand() {
        List<Album.AlbumInfo> result = library.getAlbums().stream()
                .filter(album -> album.getOwner().equals(this.username))
                .map(Album.AlbumInfo::new).toList();

        return new ResponseResultAlbums(this, result);
    }
}
