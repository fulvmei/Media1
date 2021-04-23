package com.chengfu.android.media.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media2.common.SessionPlayer;

public class VideoView extends FrameLayout {

    public static final int VIEW_TYPE_SURFACE_VIEW = 0;
    public static final int VIEW_TYPE_TEXTURE_VIEW = 1;

    @Nullable
    SessionPlayer player;

    public VideoView(@NonNull Context context) {
        this(context, null);
    }

    public VideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (isInEditMode()) {
            setBackgroundResource(R.color.edit_mode_bg);
        } else {

        }
    }

    @Nullable
    public SessionPlayer getPlayer() {
        return player;
    }

    public void setPlayer(@Nullable SessionPlayer player) {
        this.player = player;
//        player.setSurface()
    }
}
