package com.chengfu.android.media.demos.exo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSource;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;

import java.util.Collections;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    //    String url = "https://movement.gzstv.com/sv/stream_url/-wRU5XwUD1p-/";
//    String url = "https://media.gzstv.com/videos/196bac578cde100d06dfd434c1b68f03.mp4";
//    String url = "https://media.gzstv.com/videos/d70b605ecaa27b43b2eda13883155b2b.mp4";
    String url = "https://media.gzstv.com/videos/2022/04/28/45ad06ff307345abbfc2304376eb7a46.mp4";
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
                MediaMetadata ms = new MediaMetadata.Builder()
                        .setTitle("FFFFFFFFFFFFFFFF" + Math.random())
                        .build();
//                /sdcard/kk.mp4
                player.setPlaylistMetadata(ms);
                MediaItem asa = mediaItem.buildUpon()
                        .setMediaMetadata(ms)
                        .build();
                player.addMediaItem(asa);
            }
        });
        playerView = findViewById(R.id.playView);

        mediaItem = MediaItem.fromUri(url);

        test();

    }

    @Override
    protected void onResume() {
        super.onResume();
        playerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        playerView.onPause();
    }

    private void test() {
        OkHttpClient okHttpClient = new OkHttpClient();

//        DataSource.Factory dataSourceFactory = new DefaultMediaSourceFactory(this);
//        MediaSourceFactory mediaSourceFactory = new DefaultMediaSourceFactory(dataSourceFactory);
//        new OkHttpDataSource.Factory(new Call.Factory() {
//            @NonNull
//            @Override
//            public Call newCall(@NonNull Request request) {
//                return null;
//            }
//        });
        DataSource.Factory dataSourceFactory = new OkHttpDataSource.Factory(new Call.Factory() {
            @NonNull
            @Override
            public Call newCall(@NonNull Request request) {
                return okHttpClient.newCall(request);
            }
        });
        player = new ExoPlayer.Builder(this)
                .setMediaSourceFactory(new DefaultMediaSourceFactory(dataSourceFactory))
                .build();
//        player = new SimpleExoPlayer.Builder(this)
////                .setMediaSourceFactory(new DefaultMediaSourceFactory(new DefaultDataSourceFactory(this, new FuHttpDataSource.Factory(okHttpClient))))
//                .setSeekParameters(SeekParameters.PREVIOUS_SYNC).build();
////        player.play();

        player.setPlayWhenReady(true);
        player.setMediaItem(mediaItem);
        player.prepare();

        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                Log.e("exo_player", "onPlaybackStateChanged state=" + state);
            }

            @Override
            public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {
                Log.e("exo_player", "onPlayWhenReadyChanged playWhenReady=" + playWhenReady + ",reason=" + reason);
            }

            @Override
            public void onIsPlayingChanged(boolean isPlaying) {
                Log.e("exo_player", "onIsPlayingChanged isPlaying=" + isPlaying);
            }

            @Override
            public void onPlayerError(PlaybackException error) {
                Log.e("exo_player", "onPlayerError error=" + error);
            }

            @Override
            public void onPlayerErrorChanged(@Nullable PlaybackException error) {
                Log.e("exo_player", "onPlayerErrorChanged error=" + error);
            }

            @Override
            public void onMediaItemTransition(@Nullable MediaItem mediaItem, int reason) {
                Log.e("exo_player", "onMediaItemTransition mediaItem=" + mediaItem);
            }

            @Override
            public void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                Log.e("exo_player", "onMediaMetadataChanged mediaMetadata=" + mediaMetadata);
            }

            @Override
            public void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
                Log.e("exo_player", "onPlaylistMetadataChanged mediaMetadata=" + mediaMetadata);
            }

            @Override
            public void onMetadata(Metadata metadata) {
                Log.e("exo_player", "onMetadata metadata=" + metadata);
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