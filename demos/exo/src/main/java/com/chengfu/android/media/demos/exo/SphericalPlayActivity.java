package com.chengfu.android.media.demos.exo;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class SphericalPlayActivity extends AppCompatActivity {

//    final static String url = "https://media.gzstv.com/videos/d70b605ecaa27b43b2eda13883155b2b.mp4";
    final static String url = "rtmp://live-rtmp2.utovr.com/utovr2/21754903804";

    Player player;
    PlayerView playerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_spherical_play);

        playerView = findViewById(R.id.playView);

        player = new SimpleExoPlayer.Builder(this).build();
        player.setMediaItem(MediaItem.fromUri(url));
        player.prepare();

        playerView.setPlayer(player);
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.setPlayWhenReady(true);
        playerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.setPlayWhenReady(false);
        playerView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }
}
