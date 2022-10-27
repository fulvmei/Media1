package com.chengfu.android.exoplayer;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;
import java.util.List;

public final class ExoMediaPlayer extends BaseMediaPlayer {

    final Player player;
    final List<MediaItem> mediaItemList;
    MediaItemSource mediaItemSource;

//    MediaItemList mPlaylist = new MediaItemList();

    MediaItem curPlaylistItem;

    public ExoMediaPlayer(@NonNull Context context) {
        this(context, null);
    }

    public ExoMediaPlayer(@NonNull Context context, @Nullable MediaItemSource mediaItemSource) {
        this.mediaItemSource = mediaItemSource;
        player = new SimpleExoPlayer.Builder(context).build();
        player.addListener(new ExoEventListener());
        mediaItemList = new ArrayList<>();
    }

    @Override
    public void prepare() {

        player.prepare();
    }

    @Override
    public void play() {
        player.setPlayWhenReady(true);
    }

    @Override
    public void pause() {
        player.setPlayWhenReady(false);
    }

    @Override
    public void seekTo(long position) {
        player.seekTo(position);
    }

    @Override
    public void setMediaItem(@NonNull MediaItem item) {
        mediaItemList.clear();
        mediaItemList.add(item);
    }

    static class ExoEventListener implements Player.EventListener {

    }

}
