package com.chengfu.android.media.demos.media3;

import static androidx.media3.common.MediaMetadata.FOLDER_TYPE_NONE;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionService;

public class PlaybackService1 extends MediaSessionService {

    private Player player;
    MediaSession mediaSession;

    @Override
    public void onCreate() {
        super.onCreate();
        player = new ExoPlayer.Builder(this)
                .setAudioAttributes(AudioAttributes.DEFAULT, /* handleAudioFocus= */ true)
                .build();

//         mediaSession = new MediaSession.Builder(this,player)
//                .setMediaItemFiller(new MediaSession.MediaItemFiller() {
//                    @NonNull
//                    @Override
//                    public MediaItem fillInLocalConfiguration(MediaSession session, MediaSession.ControllerInfo controller, MediaItem mediaItem) {
//                        return buildMediaItem();
//                    }
//                })
//                .build();
    }

    @Nullable
    @Override
    public MediaSession onGetSession(@NonNull MediaSession.ControllerInfo controllerInfo) {
        return mediaSession;
    }

    String path= "https://qn-live.gzstv.com/icvkuzqj/xinwen.m3u8";

    String image= "https://storage.googleapis.com/uamp/The_Kyoto_Connection_-_Wake_Up/art.jpg";

    private MediaItem buildMediaItem(){
        // TODO(b/194280027): add artwork
        MediaMetadata metadata = new MediaMetadata.Builder()
                .setAlbumTitle("album")
                .setTitle("title")
                .setArtist("artist")
                .setGenre("genre")
                .setFolderType(FOLDER_TYPE_NONE)
                .setIsPlayable(true)
                .setArtworkUri(Uri.parse(image))
                .build();
        return new MediaItem.Builder()
                .setMediaId(path)
                .setMediaMetadata(metadata)
                .setUri(path)
                .build();
    }
}
