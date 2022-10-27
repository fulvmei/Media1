package com.chengfu.android.media.demos.media3.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.Player;

public class MediaPlayView extends FrameLayout implements PlayerHolder {

    public MediaPlayView(@NonNull Context context) {
        super(context);
    }

    public MediaPlayView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MediaPlayView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MediaPlayView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Nullable
    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public void setPlayer(@Nullable Player player) {

    }
}
