package com.chengfu.android.media.demos.main;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.chengfu.android.media.ui.PlayerControlView;
import com.chengfu.android.media.ui.PlayerRenderView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;


public class MainActivity extends AppCompatActivity {

    private PlayerRenderView playerRenderView;
    private PlayerControlView playerControlView;

    private String url = "https://mov.bn.netease.com/open-movie/nos/mp4/2015/08/27/SB13F5AGJ_sd.mp4";
    private Player player;

    private String[] starArray = {"水星","金星","地球","火星","木星","土星"};

    private Spinner spinnerSurfaceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        playerRenderView = findViewById(R.id.playerRenderView);
        playerControlView = findViewById(R.id.playerControlView);

        spinnerSurfaceType=findViewById(R.id.spinnerSurfaceType);

        initSpinner();

        initPlayer();

        playerRenderView.setPlayer(player);
    }

    private void initPlayer() {
        player = new ExoPlayer.Builder(this)
                .setAudioAttributes(AudioAttributes.DEFAULT, true)
                .build();

        playerControlView.setPlayer(player);

        player.setMediaItem(MediaItem.fromUri(url));
        player.prepare();
        player.play();
    }

    private void initSpinner(){
        //声明一个下拉列表的数组适配器
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this,R.layout.item_select,starArray);
        //设置数组适配器的布局样式
        starAdapter.setDropDownViewResource(R.layout.item_dropdown);
        //从布局文件中获取名叫sp_dialog的下拉框
        //设置下拉框的标题，不设置就没有难看的标题了
        spinnerSurfaceType.setPrompt("请选择行星");
        //设置下拉框的数组适配器
        spinnerSurfaceType.setAdapter(starAdapter);
        //设置下拉框默认的显示第一项
        spinnerSurfaceType.setSelection(0);
        //给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的onItemSelected方法
        spinnerSurfaceType.setOnItemSelectedListener(new MySelectedListener());
    }

    class MySelectedListener implements AdapterView.OnItemSelectedListener{

//        @Override
//        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//            Toast.makeText(MainActivity.this,"您选择的是："+starArray[i],Toast.LENGTH_SHORT).show();
//        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}