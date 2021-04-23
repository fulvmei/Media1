package com.chengfu.android.exoplayer;

import android.content.Context;

import androidx.annotation.NonNull;

import com.chengfu.android.media.common.BaseMediaPlayer;
import com.chengfu.android.media.common.MediaItem;
import com.chengfu.android.media.common.MediaPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.common.util.concurrent.ListenableFuture;

public final class ExoMediaPlayer extends BaseMediaPlayer {

    final Player player;
    @PlayerState
    private int playerState;

//    MediaItemList mPlaylist = new MediaItemList();

    MediaItem curPlaylistItem;

    public ExoMediaPlayer(@NonNull Context context) {
        playerState = PLAYER_STATE_IDLE;
        player = new SimpleExoPlayer.Builder(context).build();
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> prepare() {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> play() {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> pause() {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> seekTo(long position) {
        return null;
    }

    @Override
    public int getPlayerState() {
        return playerState;
    }

    @Override
    public void setMediaItemProvider(MediaItemProvider mediaItemProvider) {

    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> setMediaItem(@NonNull MediaItem item) {
        com.google.android.exoplayer2.MediaItem mediaItem=com.google.android.exoplayer2.MediaItem.fromUri(item.url);
        player.setMediaItem(mediaItem);
        return null;
    }
}
