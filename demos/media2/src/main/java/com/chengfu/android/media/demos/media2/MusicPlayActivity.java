package com.chengfu.android.media.demos.media2;

import android.content.ComponentName;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.Rating;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.SubtitleData;
import androidx.media2.common.UriMediaItem;
import androidx.media2.common.VideoSize;
import androidx.media2.session.LibraryResult;
import androidx.media2.session.MediaBrowser;
import androidx.media2.session.MediaController;
import androidx.media2.session.MediaLibraryService;
import androidx.media2.session.MediaSession;
import androidx.media2.session.MediaSessionManager;
import androidx.media2.session.SessionCommand;
import androidx.media2.session.SessionCommandGroup;
import androidx.media2.session.SessionResult;
import androidx.media2.session.SessionToken;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.media2.SessionPlayerConnector;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class MusicPlayActivity extends AppCompatActivity {
    SessionPlayer sessionPlayer;
    //    String url = "https://movement.gzstv.com/sv/stream_url/-wRU5XwUD1p-/";
    String url = "https://qn-live.gzstv.com/icvkuzqj/xinwen.m3u8";
    MediaController mediaController;
    TextureView textureView;
    MediaBrowser mediaBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textureView = findViewById(R.id.textureView);

        findViewById(R.id.state).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaController.setMediaItem("1");
            }
        });
        findViewById(R.id.prepare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaController.prepare();
            }
        });

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaController.play();
            }
        });

//        mediaBrowser=new MediaBrowser(this, MediaSessionManager.getInstance(this).getSessionServiceTokens(),);
//        mediaController = new MediaController(
//                this,
//                new SessionToken(this, new ComponentName("com.chengfu.android.media.demos.media2", "com.chengfu.android.media.demos.media2.MusicService")),
//                null,
//                Executors.newFixedThreadPool(3),
//                new MediaController.ControllerCallback() {
//                    @Override
//                    public void onConnected(@NonNull MediaController controller, @NonNull SessionCommandGroup allowedCommands) {
//                        super.onConnected(controller, allowedCommands);
//                    }
//                });

        mediaController = new MediaController.Builder(this)
                .setSessionToken(new SessionToken(this, new ComponentName("com.chengfu.android.media.demos.media2", "com.chengfu.android.media.demos.media2.MusicService")))
                .setControllerCallback(Executors.newFixedThreadPool(3), controllerCallback)
                .build();
        Log.e("MusicPlayActivity", "mediaController=" + mediaController);

    }

    MediaController.ControllerCallback controllerCallback = new MediaController.ControllerCallback() {
        @Override
        public int onSetCustomLayout(@NonNull MediaController controller, @NonNull List<MediaSession.CommandButton> layout) {
            Log.e("controllerCallback", "onSetCustomLayout");
            return super.onSetCustomLayout(controller, layout);
        }

        @NonNull
        @Override
        public SessionResult onCustomCommand(@NonNull MediaController controller, @NonNull SessionCommand command, @Nullable Bundle args) {
            Log.e("controllerCallback", "onCustomCommand");
            return super.onCustomCommand(controller, command, args);
        }

        @Override
        public void onAllowedCommandsChanged(@NonNull MediaController controller, @NonNull SessionCommandGroup commands) {
            super.onAllowedCommandsChanged(controller, commands);
            Log.e("controllerCallback", "onAllowedCommandsChanged");
        }

        @Override
        public void onBufferingStateChanged(@NonNull MediaController controller, @NonNull MediaItem item, int state) {
            super.onBufferingStateChanged(controller, item, state);
            Log.e("controllerCallback", "onBufferingStateChanged");
        }

        @Override
        public void onConnected(@NonNull MediaController controller, @NonNull SessionCommandGroup allowedCommands) {
            super.onConnected(controller, allowedCommands);
            Log.e("controllerCallback", "onConnected");
        }

        @Override
        public void onCurrentMediaItemChanged(@NonNull MediaController controller, @Nullable MediaItem item) {
            super.onCurrentMediaItemChanged(controller, item);
            Log.e("controllerCallback", "onCurrentMediaItemChanged");
        }

        @Override
        public void onDisconnected(@NonNull MediaController controller) {
            super.onDisconnected(controller);
            Log.e("controllerCallback", "onDisconnected");
        }

        @Override
        public void onPlaybackCompleted(@NonNull MediaController controller) {
            super.onPlaybackCompleted(controller);
            Log.e("controllerCallback", "onPlaybackCompleted");
        }

        @Override
        public void onPlaybackInfoChanged(@NonNull MediaController controller, @NonNull MediaController.PlaybackInfo info) {
            super.onPlaybackInfoChanged(controller, info);
            Log.e("controllerCallback", "onPlaybackInfoChanged");
        }

        @Override
        public void onPlaybackSpeedChanged(@NonNull MediaController controller, float speed) {
            super.onPlaybackSpeedChanged(controller, speed);
            Log.e("controllerCallback", "onPlaybackSpeedChanged");
        }

        @Override
        public void onPlayerStateChanged(@NonNull MediaController controller, int state) {
            super.onPlayerStateChanged(controller, state);
            Log.e("controllerCallback", "onPlayerStateChanged");
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
        public void onRepeatModeChanged(@NonNull MediaController controller, int repeatMode) {
            super.onRepeatModeChanged(controller, repeatMode);
            Log.e("controllerCallback", "onRepeatModeChanged");
        }

        @Override
        public void onSeekCompleted(@NonNull MediaController controller, long position) {
            super.onSeekCompleted(controller, position);
            Log.e("controllerCallback", "onSeekCompleted");
        }

        @Override
        public void onShuffleModeChanged(@NonNull MediaController controller, int shuffleMode) {
            super.onShuffleModeChanged(controller, shuffleMode);
            Log.e("controllerCallback", "onShuffleModeChanged");
        }

        @Override
        public void onSubtitleData(@NonNull MediaController controller, @NonNull MediaItem item, @NonNull SessionPlayer.TrackInfo track, @NonNull SubtitleData data) {
            super.onSubtitleData(controller, item, track, data);
            Log.e("controllerCallback", "onSubtitleData");
        }

        @Override
        public void onTrackDeselected(@NonNull MediaController controller, @NonNull SessionPlayer.TrackInfo trackInfo) {
            super.onTrackDeselected(controller, trackInfo);
            Log.e("controllerCallback", "onTrackDeselected");
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
        public void onVideoSizeChanged(@NonNull MediaController controller, @NonNull VideoSize videoSize) {
            super.onVideoSizeChanged(controller, videoSize);
            Log.e("controllerCallback", "onVideoSizeChanged");
        }
    };
}