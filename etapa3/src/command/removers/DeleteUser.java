package command.removers;

import command.Command;
import command.response.ResponseMsg;
import data.User;
import data.Album;
import data.AudioFile;
import data.Podcast;
import data.Playlist;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public final class DeleteUser extends Command {
    @Override
    public ResponseMsg processCommand() {
        String message;

        User user = library.seekUser(username);
        for (User normalUser : library.getUsers()) {
            if (normalUser.getUserType() != User.UserType.USER || !normalUser.isOnline()) {
                continue;
            }
            normalUser.getPlayer().updatePlaying(timestamp);
        }

        if (user == null) {
            message = "The username " + username + " doesn't exist.";
        } else {
            boolean inUse = false;
            switch (user.getUserType()) {
                case ARTIST:
                    // THIS IS HORRID, PLEASE LOOK INTO FIXING THIS
                    // or at least optimizing it somewhat
                    // as there is quite a bit of duplicate code
                    for (Album album : user.getAlbumList()) {
                        for (AudioFile file : album.getSongList()) {
                            for (User normalUser : library.getUsers()) {
                                if (normalUser.getUserType() != User.UserType.USER) {
                                    continue;
                                }
                                MusicPlayer player = normalUser.getPlayer();
                                if (player.getAudioPlaying() != null
                                        && player.getAudioPlaying()
                                            .getName().equals(file.getName())) {
                                    inUse = true;
                                    break;
                                }

                                if (normalUser.getPage() != null
                                        && normalUser.getPage().getCreator() == user) {
                                    inUse = true;
                                    break;
                                }
                            }
                            if (inUse) {
                                break;
                            }
                        }
                        if (inUse) {
                            break;
                        }
                    }
                    break;
                case HOST:
                    for (Podcast podcast : user.getPodcastList()) {
                        for (AudioFile file : podcast.getEpisodes()) {
                            for (User normalUser : library.getUsers()) {
                                if (normalUser.getUserType() != User.UserType.USER) {
                                    continue;
                                }
                                MusicPlayer player = normalUser.getPlayer();
                                if (player.getAudioPlaying() != null
                                        && player.getAudioPlaying()
                                        .getName().equals(file.getName())) {
                                    inUse = true;
                                    break;
                                }

                                if (normalUser.getPage() != null
                                        && normalUser.getPage().getCreator() == user) {
                                    inUse = true;
                                    break;
                                }
                            }
                            if (inUse) {
                                break;
                            }
                        }
                        if (inUse) {
                            break;
                        }
                    }
                    break;
                case USER:
                    for (Playlist playlist : user.getPlayer().getCreatedPlaylists()) {
                        for (AudioFile file : playlist.getSongList()) {
                            for (User normalUser : library.getUsers()) {
                                if (normalUser.getUserType() != User.UserType.USER) {
                                    continue;
                                }
                                MusicPlayer player = normalUser.getPlayer();
                                if (player.getAudioPlaying() != null
                                        && player.getAudioPlaying()
                                        .getName().equals(file.getName())) {
                                    inUse = true;
                                    break;
                                }
                            }
                            if (inUse) {
                                break;
                            }
                        }
                        if (inUse) {
                            break;
                        }
                    }
                    break;
                default:
                    break;
            }

            if (inUse) {
                message = username + " can't be deleted.";
            } else {
                library.removeUser(user);
                message = username + " was successfully deleted.";
            }
        }

        return new ResponseMsg(this, message);
    }
}
