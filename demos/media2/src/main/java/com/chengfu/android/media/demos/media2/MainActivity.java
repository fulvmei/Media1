package com.chengfu.android.media.demos.media2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.SubtitleData;
import androidx.media2.common.UriMediaItem;
import androidx.media2.common.VideoSize;
import androidx.media2.player.MediaPlayer;
import androidx.media2.session.MediaBrowser;
import androidx.media2.session.MediaController;
import androidx.media2.session.MediaLibraryService;
import androidx.media2.session.MediaSession;
import androidx.media2.session.MediaSessionManager;
import androidx.media2.session.SessionCommand;
import androidx.media2.session.SessionCommandGroup;
import androidx.media2.session.SessionResult;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;


import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.media2.SessionPlayerConnector;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    SessionPlayer sessionPlayer;
    //    String url = "https://movement.gzstv.com/sv/stream_url/-wRU5XwUD1p-/";
    String url = "https://qn-live.gzstv.com/icvkuzqj/xinwen.m3u8";
    MediaController mediaController;
    TextureView textureView;
//    MediaBrowser mediaBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textureView = findViewById(R.id.textureView);

        findViewById(R.id.state).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("player", "PlayerState=" + sessionPlayer.getPlayerState());

//                Log.e("player", "getSessionServiceTokens=" + MediaSessionManager.getInstance(MainActivity.this).getSessionServiceTokens());
                Intent intent=new Intent(MainActivity.this,MusicPlayActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.prepare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListenableFuture<SessionPlayer.PlayerResult> future = sessionPlayer.prepare();
                Log.e("player", "future=" + future);
                future.addListener(() -> {
                    try {
                        SessionPlayer.PlayerResult playerResult = future.get();
                        Log.e("player", "playerResult=" + playerResult.getResultCode());
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }, Executors.newFixedThreadPool(1));
            }
        });

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListenableFuture<SessionPlayer.PlayerResult> future=sessionPlayer.play();
                Log.e("player", "future=" + future);
                future.addListener(() -> {
                    try {
                        SessionPlayer.PlayerResult playerResult = future.get();
                        Log.e("player", "playerResult=" + playerResult.getResultCode());
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }, Executors.newFixedThreadPool(1));
            }
        });


        sessionPlayer = getMediaPlayer();
        sessionPlayer.setAudioAttributes(new AudioAttributesCompat.Builder().setUsage(AudioAttributesCompat.USAGE_MEDIA).build());

        sessionPlayer.setMediaItem(new UriMediaItem.Builder(Uri.parse(url)).build());
    }

    private SessionPlayer getMediaPlayer() {
        Player exoPlayer = new SimpleExoPlayer.Builder(this).build();
        SessionPlayerConnector player = new SessionPlayerConnector(exoPlayer);

//        MediaPlayer  player=new MediaPlayer(this);
        player.registerPlayerCallback(Executors.newFixedThreadPool(3), new SessionPlayer.PlayerCallback() {
            @Override
            public void onPlayerStateChanged(@NonNull SessionPlayer player, int playerState) {
                super.onPlayerStateChanged(player, playerState);
                Log.e("player", "onPlayerStateChanged playerState=" + playerState);
            }

            @Override
            public void onBufferingStateChanged(@NonNull SessionPlayer player, @Nullable MediaItem item, int buffState) {
                super.onBufferingStateChanged(player, item, buffState);
                Log.e("player", "onBufferingStateChanged buffState=" + buffState);
            }

            @Override
            public void onPlaybackCompleted(@NonNull SessionPlayer player) {
                super.onPlaybackCompleted(player);
                Log.e("player", "onPlaybackCompleted ");
            }
        });

        return player;
    }
}