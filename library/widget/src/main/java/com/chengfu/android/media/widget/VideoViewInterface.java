package com.chengfu.android.media.widget;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.media2.common.SessionPlayer;

interface VideoViewInterface {

    boolean assignSurfaceToPlayerWrapper(SessionPlayer player);
    void setSurfaceListener(SurfaceListener l);
    int getViewType();
    boolean hasAvailableSurface();

    interface SurfaceListener {
        void onSurfaceCreated(@NonNull View view, int width, int height);
        void onSurfaceDestroyed(@NonNull View view);
        void onSurfaceChanged(@NonNull View view, int width, int height);
        void onSurfaceTakeOverDone(@NonNull VideoViewInterface view);
    }
}
