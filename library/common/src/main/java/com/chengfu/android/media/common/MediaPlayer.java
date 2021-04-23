package com.chengfu.android.media.common;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import com.google.common.util.concurrent.ListenableFuture;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A media player interface defining traditional high-level functionality, such as the ability to
 * play, pause, seek and query properties of the currently playing media.
 */
public interface MediaPlayer {

    /**
     * Playback state. One of {@link #PLAYER_STATE_IDLE}, {@link #PLAYER_STATE_PAUSED}, {@link #PLAYER_STATE_PLAYING} or
     * {@link #PLAYER_STATE_ERROR}
     */
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({PLAYER_STATE_IDLE, PLAYER_STATE_PAUSED, PLAYER_STATE_PLAYING, PLAYER_STATE_ERROR})
    @interface PlayerState {
    }

    /**
     * State when the player is idle, and needs configuration to start playback.
     */
    int PLAYER_STATE_IDLE = 0;

    /**
     * State when the player's playback is paused
     */
    int PLAYER_STATE_PAUSED = 1;

    /**
     * State when the player's playback is ongoing
     */
    int PLAYER_STATE_PLAYING = 2;

    /**
     * State when the player is in error state and cannot be recovered self.
     */
    int PLAYER_STATE_ERROR = 3;


    int BUFFERING_STATE_=0;

    @NonNull
    ListenableFuture<PlayerResult> prepare();

    @NonNull
    ListenableFuture<PlayerResult> play();

    @NonNull
    ListenableFuture<PlayerResult> pause();

    @NonNull
    ListenableFuture<PlayerResult> seekTo(long position);

    @PlayerState
    int getPlayerState();


    void setMediaItemProvider(MediaItemProvider mediaItemProvider);

    @NonNull
    ListenableFuture<PlayerResult> setMediaItem(
            @NonNull MediaItem item);

    interface PlayerResult {

    }

    interface MediaItemProvider {
        @NonNull
        ListenableFuture<MediaItem> load(@NonNull MediaItem mediaItem);
    }
}
