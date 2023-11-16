package command;

import data.AudioFile;
import data.Library;
import data.Song;
import functionality.MusicPlayer;
import functionality.SearchBar;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandModify extends Command {
    private int playlistId;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        MusicPlayer player = Library.instance.seekUser(this.username).getPlayer();
        AudioFile audio = player.getAudioPlaying();

        if (audio == null) {
            message =
                    "Please load a source before adding to or removing from the playlist.";
        } else if (player.getCreatedPlaylists().size() <= playlistId - 1) {
            message = "The specified playlist does not exist.";
        } else if (audio.getType() != SearchBar.SearchType.SONG) {
            message = "The loaded source is not a song.";
        } else {
            boolean isAdded = player.AddRemoveInPlaylist(playlistId - 1, (Song) audio);
            message = isAdded
                    ? "Successfully added to playlist."
                    : "Successfully removed from playlist.";
        }

        return new ResponseMsg(this, message);
    }
}
