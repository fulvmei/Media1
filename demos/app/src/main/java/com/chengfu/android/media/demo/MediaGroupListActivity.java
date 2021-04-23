package com.chengfu.android.media.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.chengfu.android.media.demo.util.MediaUtil;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

public class MediaGroupListActivity extends AppCompatActivity {

    MediaGroupAdapter mediaGroupAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_group_list);


        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .color(getResources().getColor(R.color.divider))
                        .size(1)
                        .build());

        mediaGroupAdapter=new MediaGroupAdapter();

        recyclerView.setAdapter(mediaGroupAdapter);

        mediaGroupAdapter.submitList(MediaUtil.getMediaGroupList(this));
    }
}