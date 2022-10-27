package com.chengfu.android.media.demos.media3.lib;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.TextureView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;

public abstract class AbstractMediaPlayView extends FrameLayout{

    @Nullable
    private Player player;

    public AbstractMediaPlayView(@NonNull Context context) {
        super(context);
    }

    public AbstractMediaPlayView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AbstractMediaPlayView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Nullable
//    @Override
//    public Player getPlayer() {
//        return player;
//    }
//
//    @Override
//    public void setPlayer(@Nullable Player player) {
//        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
//        Assertions.checkArgument(
//                player == null || player.getApplicationLooper() == Looper.getMainLooper());
//        if (this.player == player) {
//            return;
//        }
//        @Nullable Player oldPlayer = this.player;
//        if (oldPlayer != null) {
//            oldPlayer.removeListener(componentListener);
//            if (surfaceView instanceof TextureView) {
//                oldPlayer.clearVideoTextureView((TextureView) surfaceView);
//            } else if (surfaceView instanceof SurfaceView) {
//                oldPlayer.clearVideoSurfaceView((SurfaceView) surfaceView);
//            }
//        }
    }
//
