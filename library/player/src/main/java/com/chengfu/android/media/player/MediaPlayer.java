package com.chengfu.android.media.player;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.SessionPlayer;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class MediaPlayer extends SessionPlayer {

    public MediaPlayer(@NonNull Context context) {

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
    public ListenableFuture<PlayerResult> prepare() {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> seekTo(long position) {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> setPlaybackSpeed(float playbackSpeed) {
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<PlayerResult> setAudioAttributes(@NonNull AudioAttributesCompat attributes) {
        return null;
    }

    @Override
    public int getPlayerState() {
        return 0;
    }

    @Override
    public long getCurrentPosition() {
        return 0;
    }

    @Override
    public long getDuration() {
        return 0;
    }

    @Override
    public long getBufferedPosition() {
        return 0;
    }

    @Override
    public int getBufferingState() {
        return 0;
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
        return 0;
    }

    @Override
    public int getShuffleMode() {
        return 0;
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
