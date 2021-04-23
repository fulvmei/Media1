package com.chengfu.android.media.demos.media2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import androidx.media2.session.RemoteSessionPlayer;
import androidx.media2.session.SessionCommand;
import androidx.media2.session.SessionCommandGroup;
import androidx.media2.session.SessionResult;
import androidx.media2.session.SessionToken;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    //    String url = "https://movement.gzstv.com/sv/stream_url/-wRU5XwUD1p-/";
    String url = "http://r.ossrs.net/live/livestream.m3u8";
    MediaController mediaController;
    TextureView textureView;
    MediaBrowser mediaBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textureView = findViewById(R.id.textureView);
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaBrowser.play();
                Surface surface = new Surface(textureView.getSurfaceTexture());
                mediaBrowser.setSurface(surface);
                mediaBrowser.prepare();
            }
        });

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaBrowser.play();
            }
        });



//        mediaPlayer = getMediaPlayer();

//        mediaPlayer.registerPlayerCallback(Executors.newFixedThreadPool(3), new RemoteSessionPlayer.Callback() {
//            @Override
//            public void onPlayerStateChanged(@NonNull SessionPlayer player, int playerState) {
//                super.onPlayerStateChanged(player, playerState);
//                Log.e("player", "onPlayerStateChanged playerState=" + playerState);
//            }
//
//            @Override
//            public void onBufferingStateChanged(@NonNull SessionPlayer player, @Nullable MediaItem item, int buffState) {
//                super.onBufferingStateChanged(player, item, buffState);
//                Log.e("player", "onBufferingStateChanged buffState=" + buffState);
//            }
//        });

//        mediaPlayer.setMediaItem(new UriMediaItem.Builder(Uri.parse(url)).build());
//        mediaPlayer.play();
//        mediaPlayer.prepare();
//        future.addListener(() -> {
//            try {
//                Log.e("player", "PlayerResult playerResult=" + future.get());
//            } catch (ExecutionException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, Executors.newFixedThreadPool(1));
//        mediaPlayer.play();

//        MediaBrowser mediaBrowser=new MediaBrowser();

//        Intent intent = new Intent(this, MusicService.class);
//        startService(intent);
//        bindService(intent, new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName name, IBinder service) {
//                Log.e("yyyy", "onServiceConnected ");
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName name) {
//                Log.e("yyyy", "onServiceDisconnected");
//            }
//        }, BIND_AUTO_CREATE);


//        Log.e("yyyy", "getSessionServiceTokens=" + MediaSessionManager.getInstance(this).getSessionServiceTokens());

//        mediaController = new MediaController.Builder(this)
////                .setConnectionHints()
//                .setControllerCallback(Executors.newSingleThreadExecutor(), controllerCallback)
//                .setSessionToken(new SessionToken(this, new ComponentName(this, MusicService.class)))
//                .build();

        mediaBrowser=new MediaBrowser.Builder(this)
                .setControllerCallback(Executors.newSingleThreadExecutor(), browserCallback)
                .setSessionToken(new SessionToken(this, new ComponentName(this, MusicService.class)))
                .build();
    }

    private MediaPlayer getMediaPlayer() {
        MediaPlayer mediaPlayer = new MediaPlayer(this);
        mediaPlayer.registerPlayerCallback(Executors.newFixedThreadPool(3), new SessionPlayer.PlayerCallback() {
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

        return mediaPlayer;
    }

    final MediaBrowser.BrowserCallback browserCallback = new MediaBrowser.BrowserCallback() {
        @Override
        public void onConnected(@NonNull MediaController controller, @NonNull SessionCommandGroup allowedCommands) {
            super.onConnected(controller, allowedCommands);
            Log.e("controllerCallback", "onConnected");
            mediaBrowser.setMediaItem("1111");

//            mediaController.skipToNextPlaylistItem();
        }

        @Override
        public void onDisconnected(@NonNull MediaController controller) {
            super.onDisconnected(controller);
            Log.e("controllerCallback", "onDisconnected");
        }

        @Override
        public int onSetCustomLayout(@NonNull MediaController controller, @NonNull List<MediaSession.CommandButton> layout) {
            Log.e("controllerCallback", "onSetCustomLayout");
            return super.onSetCustomLayout(controller, layout);
        }

        @Override
        public void onPlaybackInfoChanged(@NonNull MediaController controller, @NonNull MediaController.PlaybackInfo info) {
            super.onPlaybackInfoChanged(controller, info);
            Log.e("controllerCallback", "onPlaybackInfoChanged");
        }

        @Override
        public void onAllowedCommandsChanged(@NonNull MediaController controller, @NonNull SessionCommandGroup commands) {
            super.onAllowedCommandsChanged(controller, commands);
            Log.e("controllerCallback", "onAllowedCommandsChanged");
        }

        @NonNull
        @Override
        public SessionResult onCustomCommand(@NonNull MediaController controller, @NonNull SessionCommand command, @Nullable Bundle args) {
            Log.e("controllerCallback", "onCustomCommand");
            return super.onCustomCommand(controller, command, args);
        }

        @Override
        public void onPlayerStateChanged(@NonNull MediaController controller, int state) {
            super.onPlayerStateChanged(controller, state);
            Log.e("controllerCallback", "onPlayerStateChanged state="+state);
        }

        @Override
        public void onPlaybackSpeedChanged(@NonNull MediaController controller, float speed) {
            super.onPlaybackSpeedChanged(controller, speed);
            Log.e("controllerCallback", "onPlaybackSpeedChanged");
        }

        @Override
        public void onBufferingStateChanged(@NonNull MediaController controller, @NonNull MediaItem item, int state) {
            super.onBufferingStateChanged(controller, item, state);
            Log.e("controllerCallback", "onBufferingStateChanged state="+state);
        }

        @Override
        public void onSeekCompleted(@NonNull MediaController controller, long position) {
            super.onSeekCompleted(controller, position);
            Log.e("controllerCallback", "onSeekCompleted");
        }

        @Override
        public void onCurrentMediaItemChanged(@NonNull MediaController controller, @Nullable MediaItem item) {
            super.onCurrentMediaItemChanged(controller, item);
            Log.e("controllerCallback", "onCurrentMediaItemChanged");
        }

        @Override
        public void onPlaylistChanged(@NonNull MediaController controller, @Nullable List<MediaItem> list, @Nullable MediaMetadata metadata) {
            super.onPlaylistChanged(controller, list, metadata);
            Log.e("controllerCallback", "onPlaylistChanged");
        }

        @Override
        public void onPlaylistMetadataChanged(@NonNull MediaController controller, @Nullable MediaMetadata metadata) {
            super.onPlaylistMetadataChanged(controller, metadata);
            Log.e("controllerCallback", "onPlaylistMetadataChanged");
        }

        @Override
        public void onShuffleModeChanged(@NonNull MediaController controller, int shuffleMode) {
            super.onShuffleModeChanged(controller, shuffleMode);
            Log.e("controllerCallback", "onShuffleModeChanged");
            ;
        }

        @Override
        public void onRepeatModeChanged(@NonNull MediaController controller, int repeatMode) {
            super.onRepeatModeChanged(controller, repeatMode);
            Log.e("controllerCallback", "onRepeatModeChanged");
        }

        @Override
        public void onPlaybackCompleted(@NonNull MediaController controller) {
            super.onPlaybackCompleted(controller);
            Log.e("controllerCallback", "onPlaybackCompleted");
        }

        @Override
        public void onVideoSizeChanged(@NonNull MediaController controller, @NonNull VideoSize videoSize) {
            super.onVideoSizeChanged(controller, videoSize);
            Log.e("controllerCallback", "onVideoSizeChanged");
        }

        @Override
        public void onTracksChanged(@NonNull MediaController controller, @NonNull List<SessionPlayer.TrackInfo> tracks) {
            super.onTracksChanged(controller, tracks);
            Log.e("controllerCallback", "onTracksChanged");
        }

        @Override
        public void onTrackSelected(@NonNull MediaController controller, @NonNull SessionPlayer.TrackInfo trackInfo) {
            super.onTrackSelected(controller, trackInfo);
            Log.e("controllerCallback", "onTrackSelected");
        }

        @Override
        public void onTrackDeselected(@NonNull MediaController controller, @NonNull SessionPlayer.TrackInfo trackInfo) {
            super.onTrackDeselected(controller, trackInfo);
            Log.e("controllerCallback", "onTrackDeselected");
        }

        @Override
        public void onSubtitleData(@NonNull MediaController controller, @NonNull MediaItem item, @NonNull SessionPlayer.TrackInfo track, @NonNull SubtitleData data) {
            super.onSubtitleData(controller, item, track, data);
            Log.e("controllerCallback", "onSubtitleData");
        }

        @Override
        public void onChildrenChanged(@NonNull MediaBrowser browser, @NonNull String parentId, int itemCount, @Nullable MediaLibraryService.LibraryParams params) {
            super.onChildrenChanged(browser, parentId, itemCount, params);
            Log.e("controllerCallback", "onChildrenChanged");
        }

        @Override
        public void onSearchResultChanged(@NonNull MediaBrowser browser, @NonNull String query, int itemCount, @Nullable MediaLibraryService.LibraryParams params) {
            super.onSearchResultChanged(browser, query, itemCount, params);
            Log.e("controllerCallback", "onSearchResultChanged");
        }
    };

    final MediaController.ControllerCallback controllerCallback = new MediaController.ControllerCallback() {
        @Override
        public void onConnected(@NonNull MediaController controller, @NonNull SessionCommandGroup allowedCommands) {
            super.onConnected(controller, allowedCommands);
            Log.e("controllerCallback", "onConnected");
            mediaController.setMediaItem("1111");
//            mediaController.skipToNextPlaylistItem();
        }

        @Override
        public void onDisconnected(@NonNull MediaController controller) {
            super.onDisconnected(controller);
            Log.e("controllerCallback", "onDisconnected");
        }

        @Override
        public int onSetCustomLayout(@NonNull MediaController controller, @NonNull List<MediaSession.CommandButton> layout) {
            Log.e("controllerCallback", "onSetCustomLayout");
            return super.onSetCustomLayout(controller, layout);
        }

        @Override
        public void onPlaybackInfoChanged(@NonNull MediaController controller, @NonNull MediaController.PlaybackInfo info) {
            super.onPlaybackInfoChanged(controller, info);
            Log.e("controllerCallback", "onPlaybackInfoChanged");
        }

        @Override
        public void onAllowedCommandsChanged(@NonNull MediaController controller, @NonNull SessionCommandGroup commands) {
            super.onAllowedCommandsChanged(controller, commands);
            Log.e("controllerCallback", "onAllowedCommandsChanged");
        }

        @NonNull
        @Override
        public SessionResult onCustomCommand(@NonNull MediaController controller, @NonNull SessionCommand command, @Nullable Bundle args) {
            Log.e("controllerCallback", "onCustomCommand");
            return super.onCustomCommand(controller, command, args);
        }

        @Override
        public void onPlayerStateChanged(@NonNull MediaController controller, int state) {
            super.onPlayerStateChanged(controller, state);
            Log.e("controllerCallback", "onPlayerStateChanged");
        }

        @Override
        public void onPlaybackSpeedChanged(@NonNull MediaController controller, float speed) {
            super.onPlaybackSpeedChanged(controller, speed);
            Log.e("controllerCallback", "onPlaybackSpeedChanged");
        }

        @Override
        public void onBufferingStateChanged(@NonNull MediaController controller, @NonNull MediaItem item, int state) {
            super.onBufferingStateChanged(controller, item, state);
            Log.e("controllerCallback", "onBufferingStateChanged");
        }

        @Override
        public void onSeekCompleted(@NonNull MediaController controller, long position) {
            super.onSeekCompleted(controller, position);
            Log.e("controllerCallback", "onSeekCompleted");
        }

        @Override
        public void onCurrentMediaItemChanged(@NonNull MediaController controller, @Nullable MediaItem item) {
            super.onCurrentMediaItemChanged(controller, item);
            Log.e("controllerCallback", "onCurrentMediaItemChanged");
        }

        @Override
        public void onPlaylistChanged(@NonNull MediaController controller, @Nullable List<MediaItem> list, @Nullable MediaMetadata metadata) {
            super.onPlaylistChanged(controller, list, metadata);
            Log.e("controllerCallback", "onPlaylistChanged");
        }

        @Override
        public void onPlaylistMetadataChanged(@NonNull MediaController controller, @Nullable MediaMetadata metadata) {
            super.onPlaylistMetadataChanged(controller, metadata);
            Log.e("controllerCallback", "onPlaylistMetadataChanged");
        }

        @Override
        public void onShuffleModeChanged(@NonNull MediaController controller, int shuffleMode) {
            super.onShuffleModeChanged(controller, shuffleMode);
            Log.e("controllerCallback", "onShuffleModeChanged");
            ;
        }

        @Override
        public void onRepeatModeChanged(@NonNull MediaController controller, int repeatMode) {
            super.onRepeatModeChanged(controller, repeatMode);
            Log.e("controllerCallback", "onRepeatModeChanged");
        }

        @Override
        public void onPlaybackCompleted(@NonNull MediaController controller) {
            super.onPlaybackCompleted(controller);
            Log.e("controllerCallback", "onPlaybackCompleted");
        }

        @Override
        public void onVideoSizeChanged(@NonNull MediaController controller, @NonNull VideoSize videoSize) {
            super.onVideoSizeChanged(controller, videoSize);
            Log.e("controllerCallback", "onVideoSizeChanged");
        }

        @Override
        public void onTracksChanged(@NonNull MediaController controller, @NonNull List<SessionPlayer.TrackInfo> tracks) {
            super.onTracksChanged(controller, tracks);
            Log.e("controllerCallback", "onTracksChanged");
        }

        @Override
        public void onTrackSelected(@NonNull MediaController controller, @NonNull SessionPlayer.TrackInfo trackInfo) {
            super.onTrackSelected(controller, trackInfo);
            Log.e("controllerCallback", "onTrackSelected");
        }

        @Override
        public void onTrackDeselected(@NonNull MediaController controller, @NonNull SessionPlayer.TrackInfo trackInfo) {
            super.onTrackDeselected(controller, trackInfo);
            Log.e("controllerCallback", "onTrackDeselected");
        }

        @Override
        public void onSubtitleData(@NonNull MediaController controller, @NonNull MediaItem item, @NonNull SessionPlayer.TrackInfo track, @NonNull SubtitleData data) {
            super.onSubtitleData(controller, item, track, data);
            Log.e("controllerCallback", "onSubtitleData");
        }
    };
}