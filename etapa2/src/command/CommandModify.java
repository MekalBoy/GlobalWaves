package command;

import command.response.ResponseMsg;
import data.AudioFile;
import data.ISelectable;
import data.Song;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandModify extends Command {
    private int playlistId;

    @Override
    public final ResponseMsg processCommand() {
        String message;

        if (!library.seekUser(this.username).isOnline()) {
            message = this.username + " is offline.";
        } else {
            MusicPlayer player = library.seekUser(this.username).getPlayer();
            AudioFile audio = player.getAudioPlaying();

            if (audio == null) {
                message =
                        "Please load a source before adding to or removing from the playlist.";
            } else if (player.getCreatedPlaylists().size() <= playlistId - 1) {
                message = "The specified playlist does not exist.";
            } else if (audio.getType() != ISelectable.SearchType.SONG) {
                message = "The loaded source is not a song.";
            } else {
                boolean isAdded = player.addRemoveInPlaylist(playlistId - 1, (Song) audio);
                message = isAdded
                        ? "Successfully added to playlist."
                        : "Successfully removed from playlist.";
            }
        }

        return new ResponseMsg(this, message);
    }
}
