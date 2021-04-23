package com.chengfu.android.media.demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chengfu.android.media.demo.bean.MediaGroup;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

public class MediaItemListActivity extends AppCompatActivity {

    public static final String KEY_MEDIA_GROUP="key_media_group";
    MediaItemAdapter adapter;
    RecyclerView recyclerView;
    MediaGroup mediaGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_item_list);


        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .color(getResources().getColor(R.color.divider))
                        .size(1)
                        .build());


        mediaGroup= (MediaGroup) getIntent().getSerializableExtra(KEY_MEDIA_GROUP);

        adapter=new MediaItemAdapter();

        recyclerView.setAdapter(adapter);

        adapter.submitList(mediaGroup.getMediaItems());
    }
}