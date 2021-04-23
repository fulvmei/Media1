package com.chengfu.android.media.demo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chengfu.android.media.demo.bean.MediaGroup;

import java.util.List;

public class MediaGroupAdapter extends RecyclerView.Adapter<MediaGroupAdapter.ViewHolder> {

    private List<MediaGroup> list;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_media_group, parent, false);
        return new ViewHolder(v);
    }

    public void submitList(List<MediaGroup> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MediaGroup item = list.get(position);
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
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
                Intent intent=new Intent(v.getContext(),MediaItemListActivity.class);
                intent.putExtra(MediaItemListActivity.KEY_MEDIA_GROUP,list.get(getAdapterPosition()));
                v.getContext().startActivity(intent);
            });
        }
    }
}
