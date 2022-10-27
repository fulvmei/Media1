package com.chengfu.android.media.ui;

import static androidx.media3.common.Player.COMMAND_SET_VIDEO_SURFACE;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.Player;
import androidx.media3.common.VideoSize;
import androidx.media3.exoplayer.video.VideoDecoderGLSurfaceView;
import androidx.media3.exoplayer.video.spherical.SphericalGLSurfaceView;
import androidx.media3.ui.AspectRatioFrameLayout;

public class PlayerRenderView extends PlayerHolderView implements IPlayerRenderView {

    private ImageView imgUnderlay;
    private AspectRatioFrameLayout contentFrame;
    private View surfaceView;
    private ImageView shutterImg;

    @SurfaceType
    private int surfaceType;
    @ResizeMode
    private int resizeMode;

    public PlayerRenderView(@NonNull Context context) {
        this(context, null);
    }

    public PlayerRenderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayerRenderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PlayerRenderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        LayoutInflater.from(context).inflate(R.layout.fu_player_render_view, this);

        initViews();

        updateSurfaceView();
    }

    private void initViews() {
        imgUnderlay = findViewById(R.id.imgUnderlay);
        contentFrame = findViewById(R.id.contentFrame);
        shutterImg = findViewById(R.id.shutterImg);
    }

    @NonNull
    @Override
    public Player.Listener getPlayerListener() {
        return new PlayerEventsHandler();
    }

    @Override
    protected void onPlayerAttached(@NonNull Player player) {
        super.onPlayerAttached(player);
        setPlayerSurface(player);
    }

    @Override
    protected void onPlayerDetached(@NonNull Player player) {
        super.onPlayerDetached(player);
        clearPlayerSurface(player);
    }

    @SurfaceType
    @Override
    public int getSurfaceType() {
        return surfaceType;
    }

    @Override
    public void setSurfaceType(@SurfaceType int surfaceType) {
        if (this.surfaceType == surfaceType) {
            return;
        }
        this.surfaceType = surfaceType;
        updateSurfaceView();
    }

    @ResizeMode
    @Override
    public int getResizeMode() {
        return resizeMode;
    }

    @Override
    public void setResizeMode(@ResizeMode int resizeMode) {
        if (this.resizeMode == resizeMode) {
            return;
        }
        this.resizeMode = resizeMode;
        contentFrame.setResizeMode(resizeMode);
    }

    private void updateSurfaceView() {
        if (surfaceView != null && surfaceView.getParent() instanceof AspectRatioFrameLayout) {
            contentFrame.removeView(surfaceView);
        }
        switch (surfaceType) {
            case SURFACE_TYPE_TEXTURE_VIEW:
                surfaceView = new TextureView(getContext());
                break;
            case SURFACE_TYPE_SURFACE_VIEW:
                surfaceView = new SurfaceView(getContext());
                break;
            case SURFACE_TYPE_SPHERICAL_GL_SURFACE_VIEW:
                surfaceView = new SphericalGLSurfaceView(getContext());
                break;
            case SURFACE_TYPE_VIDEO_DECODER_GL_SURFACE_VIEW:
                surfaceView = new VideoDecoderGLSurfaceView(getContext());
                break;
        }
        ViewGroup.LayoutParams params =
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        surfaceView.setLayoutParams(params);
        contentFrame.addView(surfaceView, 0);

        if (getPlayer() != null) {
            setPlayerSurface(getPlayer());
        }
    }

    private void setPlayerSurface(@NonNull Player player) {
        if (player.isCommandAvailable(COMMAND_SET_VIDEO_SURFACE)) {
            if (surfaceView instanceof TextureView) {
                player.setVideoTextureView((TextureView) surfaceView);
            } else if (surfaceView instanceof SurfaceView) {
                player.setVideoSurfaceView((SurfaceView) surfaceView);
            }
        }
    }

    private void clearPlayerSurface(@NonNull Player player) {
        if (surfaceView instanceof TextureView) {
            player.clearVideoTextureView((TextureView) surfaceView);
        } else if (surfaceView instanceof SurfaceView) {
            player.clearVideoSurfaceView((SurfaceView) surfaceView);
        }
    }

    protected class PlayerEventsHandler implements Player.Listener {
        @Override
        public void onVideoSizeChanged(VideoSize videoSize) {
            Player.Listener.super.onVideoSizeChanged(videoSize);
        }

        @Override
        public void onRenderedFirstFrame() {
            if (shutterImg != null) {
                shutterImg.setVisibility(INVISIBLE);
            }
        }
    }
}
