package com.chengfu.android.media.demos.media3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.session.LibraryResult;
import androidx.media3.session.MediaBrowser;
import androidx.media3.session.SessionToken;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;

//import com.google.android.exoplayer2.ExoPlayer;
//import com.google.android.exoplayer2.MediaItem;
//import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
//import com.google.android.exoplayer2.upstream.DefaultDataSource;
//import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    public static final String path = "https://storage.googleapis.com/uamp/The_Kyoto_Connection_-_Wake_Up/06_-_No_Pain_No_Gain.mp3";

    public static final String path1 = "https://storage.googleapis.com/uamp/The_Kyoto_Connection_-_Wake_Up/03_-_Voyage_I_-_Waterfall.mp3";
//    ExoPlayer exoPlayer;


    RecyclerView recyclerView;
    MediaItemListAdapter mediaItemListAdapter;

    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mediaItemListAdapter = new MediaItemListAdapter();
        recyclerView.setAdapter(mediaItemListAdapter);

//        List<MediaItem> mediaItems = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            mediaItems.add(buildMediaItem("我是第" + (i + 1) + "项", "我是第" + (i + 1) + "个副标题"));
//        }
//
//        mediaItemListAdapter.submitList(mediaItems);
//
//        ComponentName componentName = new ComponentName(this, PlaybackService.class);
//        @SuppressLint("UnsafeOptInUsageError")
//        SessionToken sessionToken = new SessionToken(this, componentName);
//
//        ListenableFuture<MediaBrowser> mediaBrowserListenableFuture = new MediaBrowser.Builder(this, sessionToken).buildAsync();
//
//        Futures.addCallback(mediaBrowserListenableFuture, new FutureCallback<MediaBrowser>() {
//            @Override
//            public void onSuccess(MediaBrowser result) {
//                Log.e("pppp", "onSuccess result=" + result);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
////                        for (int i = 0; i < 10; i++) {
//                        result.setMediaItem(buildMediaItem(path, "dddddd"));
//                        result.prepare();
//                        result.play();
////                        }
//                    }
//                });
//                result.addListener(new Player.Listener() {
//                    @Override
//                    public void onPlaybackStateChanged(int playbackState) {
//                        Log.e("pppp", "onPlaybackStateChanged playbackState=" + playbackState);
//                    }
//                });
////                ListenableFuture<LibraryResult<Void>> l = result.subscribe("", null);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.e("pppp", "onFailure t=" + t);
//            }
//        }, Executors.newSingleThreadExecutor());

        player = new ExoPlayer.Builder(this)
                .setAudioAttributes(AudioAttributes.DEFAULT, true)
                .build();

        MediaItem mediaItem = MediaItem.fromUri(path);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }

    MediaItem buildMediaItem(String title, String subTitle) {
        return new MediaItem.Builder()
                .setMediaId(UUID.randomUUID().toString())
                .setRequestMetadata(MediaItem.RequestMetadata.EMPTY)
                .setMediaMetadata(new MediaMetadata.Builder()
                        .setTitle(title)
                        .setSubtitle(subTitle)
                        .build())
                .build();
    }
}