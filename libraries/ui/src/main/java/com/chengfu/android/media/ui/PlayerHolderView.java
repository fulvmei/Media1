package com.chengfu.android.media.ui;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;

import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.ui.PlayerControlView;

import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerHolderView extends FrameLayout implements PlayerHolder {

    @Nullable
    private Player player;
    private final Player.Listener playerListener;
    private final CopyOnWriteArrayList<OnPlayerChangedListener> onPlayerChangedListeners;

    public interface OnPlayerChangedListener {
        void onPlayerChanged(Player player);
    }

    public PlayerHolderView(@NonNull Context context) {
        this(context, null);
    }

    public PlayerHolderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayerHolderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PlayerHolderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        playerListener = getPlayerListener();
        onPlayerChangedListeners = new CopyOnWriteArrayList<>();
    }

    protected Player.Listener getPlayerListener() {
        return null;
    }

    public final void addOnPlayerChangedListener(OnPlayerChangedListener listener) {
        onPlayerChangedListeners.add(listener);
    }

    public final void removeOnPlayerChangedListener(OnPlayerChangedListener listener) {
        onPlayerChangedListeners.remove(listener);
    }

    public final void cleanOnPlayerChangedListeners() {
        onPlayerChangedListeners.clear();
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
            this.player.removeListener(playerListener);
            onPlayerDetached(this.player);
        }
        this.player = player;
        if (player != null) {
            player.addListener(playerListener);
            onPlayerAttached(player);
        }

    }
}
