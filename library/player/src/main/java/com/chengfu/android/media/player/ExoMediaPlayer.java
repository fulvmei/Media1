package com.chengfu.android.media.player;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.SessionPlayer;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public final class ExoMediaPlayer extends SessionPlayer {

    final Player player;
    @PlayerState
    int playerState;
    @BuffState
    int bufferingState;
    boolean prepareCalled;

    public ExoMediaPlayer(@NonNull Context context) {
        playerState = PLAYER_STATE_IDLE;
        player = new SimpleExoPlayer.Builder(context).build();
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> play() {
        player.setPlayWhenReady(true);
        if (player.getPlaybackState() == Player.STATE_ENDED) {
            player.seekTo(0);
        } else if (player.getPlaybackState() == Player.STATE_IDLE && player.getPlayerError() != null) {
            player.prepare();
        }
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> pause() {
        player.setPlayWhenReady(false);
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> prepare() {
        player.prepare();
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> seekTo(long position) {
        player.seekTo(position);
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> setPlaybackSpeed(float playbackSpeed) {
//        player.setPlaybackParameters();
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> setAudioAttributes(@NonNull AudioAttributesCompat attributes) {
//        player.getAudioComponent().setAudioAttributes(attributes);
        return null;
    }

    @Override
    public int getPlayerState() {
        return playerState;
    }

    @Override
    public long getCurrentPosition() {
        return player.getCurrentPosition();
    }

    @Override
    public long getDuration() {
        return player.getDuration();
    }

    @Override
    public long getBufferedPosition() {
        return player.getBufferedPosition();
    }

    @Override
    public int getBufferingState() {
        return bufferingState;
    }

    @Override
    public float getPlaybackSpeed() {
        return 0;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> setPlaylist(@NonNull List<MediaItem> list, @Nullable MediaMetadata metadata) {
        return null;
    }

    @Nullable
    @Override
    public AudioAttributesCompat getAudioAttributes() {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> setMediaItem(@NonNull MediaItem item) {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> addPlaylistItem(int index, @NonNull MediaItem item) {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> removePlaylistItem(int index) {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> replacePlaylistItem(int index, @NonNull MediaItem item) {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> skipToPreviousPlaylistItem() {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> skipToNextPlaylistItem() {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> skipToPlaylistItem(int index) {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> updatePlaylistMetadata(@Nullable MediaMetadata metadata) {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> setRepeatMode(int repeatMode) {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> setShuffleMode(int shuffleMode) {
        return null;
    }

    @Nullable
    @Override
    public List<MediaItem> getPlaylist() {
        return null;
    }

    @Nullable
    @Override
    public MediaMetadata getPlaylistMetadata() {
        return null;
    }

    @Override
    public int getRepeatMode() {
        return REPEAT_MODE_ONE;
    }

    @Override
    public int getShuffleMode() {
        return SHUFFLE_MODE_NONE;
    }

    @Nullable
    @Override
    public MediaItem getCurrentMediaItem() {
        return null;
    }

    @Override
    public int getCurrentMediaItemIndex() {
        return 0;
    }

    @Override
    public int getPreviousMediaItemIndex() {
        return 0;
    }

    @Override
    public int getNextMediaItemIndex() {
        return 0;
    }
}
