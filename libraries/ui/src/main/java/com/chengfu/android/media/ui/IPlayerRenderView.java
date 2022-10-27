package com.chengfu.android.media.ui;

import androidx.annotation.IntDef;
import androidx.media3.ui.AspectRatioFrameLayout;

public interface IPlayerRenderView extends PlayerHolder {

    @IntDef({SURFACE_TYPE_TEXTURE_VIEW, SURFACE_TYPE_SURFACE_VIEW, SURFACE_TYPE_SPHERICAL_GL_SURFACE_VIEW, SURFACE_TYPE_VIDEO_DECODER_GL_SURFACE_VIEW})
    @interface SurfaceType {
    }

    int SURFACE_TYPE_TEXTURE_VIEW = 0;
    int SURFACE_TYPE_SURFACE_VIEW = 1;
    int SURFACE_TYPE_SPHERICAL_GL_SURFACE_VIEW = 2;
    int SURFACE_TYPE_VIDEO_DECODER_GL_SURFACE_VIEW = 3;

    @IntDef({RESIZE_MODE_FIT, RESIZE_MODE_FIXED_WIDTH, RESIZE_MODE_FIXED_HEIGHT, RESIZE_MODE_FILL, RESIZE_MODE_ZOOM})
    @interface ResizeMode {
    }

    int RESIZE_MODE_FIT = AspectRatioFrameLayout.RESIZE_MODE_FIT;
    int RESIZE_MODE_FIXED_WIDTH = AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH;
    int RESIZE_MODE_FIXED_HEIGHT = AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT;
    int RESIZE_MODE_FILL = AspectRatioFrameLayout.RESIZE_MODE_FILL;
    int RESIZE_MODE_ZOOM = AspectRatioFrameLayout.RESIZE_MODE_ZOOM;

    @SurfaceType
    int getSurfaceType();

    void setSurfaceType(@SurfaceType int surfaceType);

    @ResizeMode
    int getResizeMode();

    void setResizeMode(@ResizeMode int resizeMode);
}
