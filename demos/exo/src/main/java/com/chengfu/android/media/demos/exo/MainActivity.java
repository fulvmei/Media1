package com.chengfu.android.media.demos.exo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class MainActivity extends AppCompatActivity {

//    String url = "https://movement.gzstv.com/sv/stream_url/-wRU5XwUD1p-/";
        String url="http://r.ossrs.net/live/livestream.m3u8";
    Player player;
    PlayerView playerView;
    MediaItem mediaItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setMediaItem(mediaItem, 10000);
            }
        });

        playerView = findViewById(R.id.playView);

        mediaItem = MediaItem.fromUri(url);
        player = new SimpleExoPlayer.Builder(this).setSeekParameters(SeekParameters.PREVIOUS_SYNC).build();

        player.play();
        player.setMediaItem(mediaItem);

        player.prepare();

        player.addListener(new Player.EventListener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                Log.e("exo_player", "onPlaybackStateChanged state=" + state);
                if (state == Player.STATE_ENDED) {
                    player.seekTo(0);
                }
            }

            @Override
            public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {
                Log.e("exo_player", "onPlayWhenReadyChanged playWhenReady=" + playWhenReady + ",reason=" + reason);
            }

            @Override
            public void onIsPlayingChanged(boolean isPlaying) {
                Log.e("exo_player", "onIsPlayingChanged isPlaying=" + isPlaying);
            }
        });
        playerView.setPlayer(player);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }
}