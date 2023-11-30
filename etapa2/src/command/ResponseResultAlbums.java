package command;

import data.Album;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResponseResultAlbums extends Response {
    private List<Album.AlbumInfo> result;

    public ResponseResultAlbums(final Command command, final List<Album.AlbumInfo> result) {
        super(command);
        this.result = result;
    }
}
