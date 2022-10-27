package com.chengfu.android.media.ui;

import androidx.annotation.Nullable;
import androidx.media3.common.Player;

public interface PlayerHolder {

    @Nullable
    Player getPlayer();

    void setPlayer(@Nullable Player player);
}
