package com.chengfu.android.media.ui;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;

public class PlayerControlView extends PlayerHolderView {

    public PlayerControlView(@NonNull Context context) {
        this(context, null);
    }

    public PlayerControlView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayerControlView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        @LayoutRes
        int layoutResourcesId = getLayoutResourcesId();
        if (layoutResourcesId != 0) {
            LayoutInflater.from(context).inflate(getLayoutResourcesId(), this);
        }
    }

    @LayoutRes
    protected int getLayoutResourcesId() {
        return R.layout.fu_player_control_view;
    }

    @NonNull
    @Override
    public Player.Listener getPlayerListener() {
        return new PlayerEventsHandler();
    }

    protected class PlayerEventsHandler implements Player.Listener {
        @Override
        public void onPlaybackStateChanged(int playbackState) {
            Log.d("tttt", "onPlaybackStateChanged->playbackState=" + playbackState);
        }

        @Override
        public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {
            Log.d("tttt", "onPlayWhenReadyChanged->playWhenReady=" + playWhenReady + ",reason=" + reason);
        }

    }
}
