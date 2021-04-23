package com.chengfu.android.media.demo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chengfu.android.media.demo.bean.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import java.util.List;
import java.util.concurrent.Future;

public class MediaPlayActivity extends AppCompatActivity {
    public static final String KEY_MEDIA_ITEM = "key_media_item";
    MediaItem mediaItem;
    Player player;
    StyledPlayerView playerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_media_play);
        mediaItem = (MediaItem) getIntent().getSerializableExtra(KEY_MEDIA_ITEM);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean dynamic= player.isCurrentWindowDynamic();
                boolean live= player.isCurrentWindowLive();
                boolean seekable= player.isCurrentWindowSeekable();
                Log.e("eee", "dynamic="+ dynamic);
                Log.e("eee", "live="+ live);
                Log.e("eee", "seekable="+ seekable);
            }
        });

        playerView = findViewById(R.id.playerView);
        player = new SimpleExoPlayer.Builder(this).build();
        player.addListener(new Player.EventListener() {
            @Override
            public void onMediaItemTransition(@Nullable com.google.android.exoplayer2.MediaItem mediaItem, int reason) {
                Log.e("eee", "onMediaItemTransition reason="+ reason);
            }

            @Override
            public void onStaticMetadataChanged(List<Metadata> metadataList) {
                Log.e("eee", "onStaticMetadataChanged metadataList="+ metadataList);
            }

            @Override
            public void onIsLoadingChanged(boolean isLoading) {
                Log.e("eee", "onIsLoadingChanged reason="+ isLoading);
            }

            @Override
            public void onPlaybackStateChanged(int state) {
                Log.e("eee", "onPlaybackStateChanged state="+state);
            }

            @Override
            public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {
                Log.e("eee", "onPlayWhenReadyChanged playWhenReady=" + playWhenReady + ",reason=" + reason);
            }

            @Override
            public void onIsPlayingChanged(boolean isPlaying) {
                Log.e("eee", "onIsPlayingChanged isPlaying=" + isPlaying);
            }

            @Override
            public void onPositionDiscontinuity(int reason) {
                Log.e("eee", "onPositionDiscontinuity reason=" + reason);
            }
        });

        playerView.setPlayer(player);

        player.setMediaItem(com.google.android.exoplayer2.MediaItem.fromUri(mediaItem.getUri()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }
}
