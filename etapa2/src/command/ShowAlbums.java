package command;

import data.Album;
import data.Library;
import data.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ShowAlbums extends Command {
    @Override
    public ResponseResultAlbums processCommand() {
        List<Album.AlbumInfo> result = Library.instance.getAlbums().stream()
                .filter(album -> album.getOwner().equals(this.username))
                .map(Album.AlbumInfo::new).toList();

        return new ResponseResultAlbums(this, result);
    }
}