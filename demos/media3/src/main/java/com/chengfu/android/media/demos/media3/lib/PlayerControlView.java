package com.chengfu.android.media.demos.media3.lib;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;

public class PlayerControlView extends FrameLayout implements PlayerHolder {

    @Nullable
    private Player player;
    @NonNull
    private final PlayerEventsHandler playerEventsHandler;

    public PlayerControlView(@NonNull Context context) {
        this(context, null);
    }

    public PlayerControlView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayerControlView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        playerEventsHandler = getPlayerEventsHandler();
    }

    @NonNull
    protected PlayerEventsHandler getPlayerEventsHandler() {
        return new PlayerEventsHandler();
    }

    protected void onPlayerAttached(@NonNull Player player) {

    }

    protected void onPlayerDetached(@NonNull Player player) {

    }

    @Nullable
    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(@Nullable Player player) {
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        Assertions.checkArgument(
                player == null || player.getApplicationLooper() == Looper.getMainLooper());
        if (this.player == player) {
            return;
        }
        if (this.player != null) {
            this.player.removeListener(playerEventsHandler);
            onPlayerDetached(this.player);
        }
        this.player = player;
        if (player != null) {
            player.addListener(playerEventsHandler);
            onPlayerAttached(player);
        }
//        updateAll();
    }

    protected class PlayerEventsHandler implements Player.Listener {

    }

}
