package com.chengfu.android.media.demos.media3.lib;

import androidx.annotation.Nullable;
import androidx.media3.common.Player;

public interface PlayerHolder {

    @Nullable
    Player getPlayer();

    void setPlayer(@Nullable Player player);
}
