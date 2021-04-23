package com.chengfu.android.media.demo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chengfu.android.media.demo.bean.MediaItem;

import java.util.List;

public class MediaItemAdapter extends RecyclerView.Adapter<MediaItemAdapter.ViewHolder> {

    private List<MediaItem> list;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_media_item, parent, false);
        return new ViewHolder(v);
    }

    public void submitList(List<MediaItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MediaItem item = list.get(position);
        holder.name.setText(item.getName());
        holder.description.setText(item.getUri());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), MediaPlayActivity.class);
                intent.putExtra(MediaPlayActivity.KEY_MEDIA_ITEM, list.get(getAdapterPosition()));
                v.getContext().startActivity(intent);
            });
        }
    }
}
