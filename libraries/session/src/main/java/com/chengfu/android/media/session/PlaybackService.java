package com.chengfu.android.media.session;

import static androidx.media3.common.MediaMetadata.FOLDER_TYPE_NONE;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.session.LibraryResult;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlaybackService extends MediaLibraryService {

    private static final String TAG = "PlaybackService";

    private Player player;

    private MediaLibrarySession mediaLibrarySession;

    @Override
    public void onCreate() {
        super.onCreate();

        player = onGetPlayer();
        mediaLibrarySession = new MediaLibrarySession.Builder(this, player, onGetMediaLibrarySessionCallback())
                .build();
    }

    @Nullable
    @Override
    public MediaLibrarySession onGetSession(MediaSession.ControllerInfo controllerInfo) {
        return mediaLibrarySession;
    }

    protected Player onGetPlayer() {
        return new ExoPlayer.Builder(this)
                .setAudioAttributes(AudioAttributes.DEFAULT, true)
                .build();
    }

    protected MediaLibrarySessionCallback onGetMediaLibrarySessionCallback() {
        return new MediaLibrarySessionCallback();
    }

    @Override
    public void onDestroy() {
        player.release();
        mediaLibrarySession.release();
        super.onDestroy();
    }

    String path = "https://qn-live.gzstv.com/icvkuzqj/xinwen.m3u8";

    String image = "https://storage.googleapis.com/uamp/The_Kyoto_Connection_-_Wake_Up/art.jpg";

    private List<MediaItem> buildMediaItem(List<MediaItem> mediaItems) {
        Log.e("pppp", "buildMediaItem mediaItems=" + mediaItems);
        List<MediaItem> items = new ArrayList<>();
        for (int i = 0; i < mediaItems.size(); i++) {
            MediaMetadata metadata = new MediaMetadata.Builder()
                    .setAlbumTitle("album")
                    .setTitle("title")
                    .setArtist("artist")
                    .setGenre("genre")
                    .setFolderType(FOLDER_TYPE_NONE)
                    .setIsPlayable(true)
                    .setArtworkUri(Uri.parse(image))
                    .build();
            items.add(new MediaItem.Builder()
                    .setMediaId(UUID.randomUUID().toString())
                    .setMediaId(path)
                    .setMediaMetadata(metadata)
                    .setUri(path)
                    .build());
        }
        return items;
    }

    protected class MediaLibrarySessionCallback implements MediaLibrarySession.Callback {
        @NonNull
        @Override
        public ListenableFuture<List<MediaItem>> onAddMediaItems(@NonNull MediaSession mediaSession, @NonNull MediaSession.ControllerInfo controller, @NonNull List<MediaItem> mediaItems) {
            Log.e("pppp", "onAddMediaItems mediaItems=" + mediaItems);
//            return Futures.submit(() -> {
//                Thread.sleep(3000);
//                return buildMediaItem(mediaItems);
//            }, Executors.newSingleThreadExecutor());
            return Futures.immediateFuture(buildMediaItem(mediaItems));
        }

        @NonNull
        @Override
        public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> onGetChildren(@NonNull MediaLibrarySession session, @NonNull MediaSession.ControllerInfo browser, @NonNull String parentId, int page, int pageSize, @Nullable LibraryParams params) {
            return MediaLibrarySession.Callback.super.onGetChildren(session, browser, parentId, page, pageSize, params);
        }

        @Override
        public ListenableFuture<LibraryResult<MediaItem>> onGetLibraryRoot(MediaLibrarySession session, MediaSession.ControllerInfo browser, @Nullable LibraryParams params) {
            return MediaLibrarySession.Callback.super.onGetLibraryRoot(session, browser, params);
        }
    }

//    private final MediaLibrarySession.MediaLibrarySessionCallback mediaLibrarySessionCallback = new MediaLibrarySession.MediaLibrarySessionCallback() {
//        @NonNull
//        @SuppressLint("UnsafeOptInUsageError")
//        @Override
//        public ListenableFuture<LibraryResult<MediaItem>> onGetLibraryRoot(@NonNull MediaLibrarySession session, @NonNull MediaSession.ControllerInfo browser, @Nullable LibraryParams params) {
//            Log.d(TAG, "onGetLibraryRoot");
//            return Futures.immediateFuture(LibraryResult.ofError(RESULT_ERROR_NOT_SUPPORTED));
//        }
//
//        @Override
//        public ListenableFuture<LibraryResult<MediaItem>> onGetItem(@NonNull MediaLibrarySession session, @NonNull MediaSession.ControllerInfo browser, @NonNull String mediaId) {
//            Log.d(TAG, "onGetItem");
//            return null;
//        }
//
//        @Override
//        public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> onGetChildren(@NonNull MediaLibrarySession session, @NonNull MediaSession.ControllerInfo browser, @NonNull String parentId, int page, int pageSize, @Nullable LibraryParams params) {
//            Log.d(TAG, "onGetChildren");
//            return null;
//        }
//
//        @NonNull
//        @SuppressLint("UnsafeOptInUsageError")
//        @Override
//        public ListenableFuture<LibraryResult<Void>> onSearch(@NonNull MediaLibrarySession session, @NonNull MediaSession.ControllerInfo browser, @NonNull String query, @Nullable LibraryParams params) {
//            Log.d(TAG, "onSearch");
//            mediaLibrarySession.notifyChildrenChanged("www", 0, params);
//            return Futures.immediateFuture(LibraryResult.ofError(RESULT_ERROR_NOT_SUPPORTED));
//        }
//    };
//
//
}
