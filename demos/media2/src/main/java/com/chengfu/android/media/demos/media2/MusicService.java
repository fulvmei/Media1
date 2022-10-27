package com.chengfu.android.media.demos.media2;

import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.Rating;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.UriMediaItem;
import androidx.media2.player.MediaPlayer;
import androidx.media2.session.LibraryResult;
import androidx.media2.session.MediaLibraryService;
import androidx.media2.session.MediaSession;
import androidx.media2.session.MediaSessionService;
import androidx.media2.session.RemoteSessionPlayer;
import androidx.media2.session.SessionCommand;
import androidx.media2.session.SessionCommandGroup;
import androidx.media2.session.SessionResult;

import java.util.List;
import java.util.concurrent.Executors;

public class MusicService extends MediaLibraryService {
    SessionPlayer sessionPlayer;
    //    MediaLibrarySession mediaLibrarySession;
    MediaLibrarySession mediaSession;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MusicService", "onCreate");
        sessionPlayer = new MediaPlayer(this);
        sessionPlayer.setAudioAttributes(new AudioAttributesCompat.Builder().setUsage(AudioAttributesCompat.USAGE_MEDIA).build());
        sessionPlayer.registerPlayerCallback(Executors.newSingleThreadExecutor(), new RemoteSessionPlayer.Callback() {
            @Override
            public void onPlayerStateChanged(@NonNull SessionPlayer player, int playerState) {
                super.onPlayerStateChanged(player, playerState);
                Log.e("sessionPlayer", "onPlayerStateChanged");
            }

            @Override
            public void onBufferingStateChanged(@NonNull SessionPlayer player, @Nullable MediaItem item, int buffState) {
                super.onBufferingStateChanged(player, item, buffState);
                Log.e("sessionPlayer", "onBufferingStateChanged");
            }

            @Override
            public void onCurrentMediaItemChanged(@NonNull SessionPlayer player, @NonNull MediaItem item) {
                super.onCurrentMediaItemChanged(player, item);
                Log.e("sessionPlayer", "onCurrentMediaItemChanged");
            }

            @Override
            public void onPlaylistChanged(@NonNull SessionPlayer player, @Nullable List<MediaItem> list, @Nullable MediaMetadata metadata) {
                super.onPlaylistChanged(player, list, metadata);
                Log.e("sessionPlayer", "onPlaylistChanged");
            }

            @Override
            public void onPlaylistMetadataChanged(@NonNull SessionPlayer player, @Nullable MediaMetadata metadata) {
                super.onPlaylistMetadataChanged(player, metadata);
                Log.e("sessionPlayer", "onPlaylistMetadataChanged");
            }
        });


        sessionPlayer.setMediaItem(new UriMediaItem.Builder(Uri.parse("https://media.gzstv.com/audio/434a8d3ebdbe9b71a0e0a7bf9e209f60.mp3")).setMetadata(new MediaMetadata.Builder().putString(MediaMetadata.METADATA_KEY_MEDIA_ID, "999").build()).build());

        mediaSession = new MediaLibrarySession.Builder(this, sessionPlayer,Executors.newFixedThreadPool(3), sessionCallback)
                .setId("fu")
                .build();
//
//        mediaLibrarySession = new MediaLibrarySession.Builder(this, sessionPlayer, Executors.newFixedThreadPool(3), sessionCallback)
//                .build();
    }

//    @Nullable
//    @Override
//    public MediaSession onGetSession(@NonNull MediaSession.ControllerInfo controllerInfo) {
//        Log.e("MusicService", "onGetSession controllerInfo=" + controllerInfo);
//        return mediaSession;
////        return null;
//    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MusicService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(@NonNull Intent intent) {
        Log.e("MusicService", "onBind action=" + intent.getAction());
        return super.onBind(intent);
    }

    @Nullable
    @Override
    public MediaLibrarySession onGetSession(@NonNull MediaSession.ControllerInfo controllerInfo) {
        return mediaSession;
    }

    @Nullable
    @Override
    public MediaNotification onUpdateNotification(@NonNull MediaSession session) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            ContextCompat.startForegroundService(this, new Intent(this, MusicService.class));
//        }
        Log.e("MusicService", "onUpdateNotification session=" + session);
        return super.onUpdateNotification(session);
    }

    @Override
    public void onDestroy() {
        Log.e("MusicService", "onDestroy");
        super.onDestroy();
    }

//    @Nullable
//    @Override
//    public MediaLibrarySession onGetSession(@NonNull MediaSession.ControllerInfo controllerInfo) {
//        return mediaLibrarySession;
//    }

    MediaLibrarySession.MediaLibrarySessionCallback sessionCallback = new MediaLibrarySession.MediaLibrarySessionCallback() {

        @Override
        public int onCommandRequest(@NonNull MediaSession session, @NonNull MediaSession.ControllerInfo controller, @NonNull SessionCommand command) {
            Log.e("sessionCallback", "onCommandRequest command=" + command.getCommandCode());
            switch (command.getCommandCode()) {
                case SessionCommand.COMMAND_CODE_PLAYER_PREPARE:
//                    onCreateMediaItem(session,controller,"1");
                    sessionPlayer.prepare();
                    break;
                case SessionCommand.COMMAND_CODE_PLAYER_PLAY:
//                    onCreateMediaItem(session,controller,"1");
                    sessionPlayer.play();
                    break;
            }
            return super.onCommandRequest(session, controller, command);
        }

        @Override
        public int onFastForward(@NonNull MediaSession session, @NonNull MediaSession.ControllerInfo controller) {
            Log.e("sessionCallback", "onFastForward");
            return super.onFastForward(session, controller);
        }

        @Override
        public int onRewind(@NonNull MediaSession session, @NonNull MediaSession.ControllerInfo controller) {
            Log.e("sessionCallback", "onRewind");
            return super.onRewind(session, controller);
        }

        @Override
        public int onSearch(@NonNull MediaLibrarySession session, @NonNull MediaSession.ControllerInfo controller, @NonNull String query, @Nullable LibraryParams params) {
            Log.e("sessionCallback", "onSearch");
            return super.onSearch(session, controller, query, params);
        }

        @Override
        public int onSetMediaUri(@NonNull MediaSession session, @NonNull MediaSession.ControllerInfo controller, @NonNull Uri uri, @Nullable Bundle extras) {
            Log.e("sessionCallback", "onSetMediaUri");
            return super.onSetMediaUri(session, controller, uri, extras);
        }

        @Override
        public int onSetRating(@NonNull MediaSession session, @NonNull MediaSession.ControllerInfo controller, @NonNull String mediaId, @NonNull Rating rating) {
            Log.e("sessionCallback", "onSetRating");
            return super.onSetRating(session, controller, mediaId, rating);
        }

        @Override
        public int onSkipBackward(@NonNull MediaSession session, @NonNull MediaSession.ControllerInfo controller) {
            Log.e("sessionCallback", "onSkipBackward");
            return super.onSkipBackward(session, controller);
        }

        @Override
        public int onSkipForward(@NonNull MediaSession session, @NonNull MediaSession.ControllerInfo controller) {
            Log.e("sessionCallback", "onSkipForward");
            return super.onSkipForward(session, controller);
        }


        @Override
        public int onSubscribe(@NonNull MediaLibrarySession session, @NonNull MediaSession.ControllerInfo controller, @NonNull String parentId, @Nullable LibraryParams params) {
            Log.e("sessionCallback", "onSubscribe");
            return super.onSubscribe(session, controller, parentId, params);
        }

        @Override
        public int onUnsubscribe(@NonNull MediaLibrarySession session, @NonNull MediaSession.ControllerInfo controller, @NonNull String parentId) {
            Log.e("sessionCallback", "onUnsubscribe");
            return super.onUnsubscribe(session, controller, parentId);
        }

        @NonNull
        @Override
        public LibraryResult onGetChildren(@NonNull MediaLibrarySession session, @NonNull MediaSession.ControllerInfo controller, @NonNull String parentId, int page, int pageSize, @Nullable LibraryParams params) {
            Log.e("sessionCallback", "onGetChildren");
            return super.onGetChildren(session, controller, parentId, page, pageSize, params);
        }

        @NonNull
        @Override
        public LibraryResult onGetItem(@NonNull MediaLibrarySession session, @NonNull MediaSession.ControllerInfo controller, @NonNull String mediaId) {
            Log.e("sessionCallback", "onGetItem");
            return super.onGetItem(session, controller, mediaId);
        }

        @NonNull
        @Override
        public LibraryResult onGetLibraryRoot(@NonNull MediaLibrarySession session, @NonNull MediaSession.ControllerInfo controller, @Nullable LibraryParams params) {
            Log.e("sessionCallback", "onGetLibraryRoot");
            return super.onGetLibraryRoot(session, controller, params);
        }

        @NonNull
        @Override
        public LibraryResult onGetSearchResult(@NonNull MediaLibrarySession session, @NonNull MediaSession.ControllerInfo controller, @NonNull String query, int page, int pageSize, @Nullable LibraryParams params) {
            Log.e("sessionCallback", "onGetSearchResult");
            return super.onGetSearchResult(session, controller, query, page, pageSize, params);
        }

        @Nullable
        @Override
        public MediaItem onCreateMediaItem(@NonNull MediaSession session, @NonNull MediaSession.ControllerInfo controller, @NonNull String mediaId) {
            Log.e("sessionCallback", "onCreateMediaItem " + Thread.currentThread());
            return new UriMediaItem.Builder(Uri.parse("https://media.gzstv.com/audio/434a8d3ebdbe9b71a0e0a7bf9e209f60.mp3")).setMetadata(new MediaMetadata.Builder().putString(MediaMetadata.METADATA_KEY_MEDIA_ID, mediaId).build()).build();
        }

        @Nullable
        @Override
        public SessionCommandGroup onConnect(@NonNull MediaSession session, @NonNull MediaSession.ControllerInfo controller) {
            Log.e("sessionCallback", "onConnect controller="+controller);
            return super.onConnect(session, controller);
//            return null;
        }

        @NonNull
        @Override
        public SessionResult onCustomCommand(@NonNull MediaSession session, @NonNull MediaSession.ControllerInfo controller, @NonNull SessionCommand customCommand, @Nullable Bundle args) {
            Log.e("sessionCallback", "onCustomCommand");
            return super.onCustomCommand(session, controller, customCommand, args);
        }

        @Override
        public void onDisconnected(@NonNull MediaSession session, @NonNull MediaSession.ControllerInfo controller) {
            Log.e("sessionCallback", "onDisconnected");
            super.onDisconnected(session, controller);
        }

        @Override
        public void onPostConnect(@NonNull MediaSession session, @NonNull MediaSession.ControllerInfo controller) {
            Log.e("sessionCallback", "onPostConnect");
            super.onPostConnect(session, controller);
        }

    };

}
