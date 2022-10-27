package com.chengfu.android.media.demos.media3;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MediaItemListAdapter extends ListAdapter<MediaItem, MediaItemListAdapter.ViewHolder> {

    static DiffUtil.ItemCallback<MediaItem> diffCallback = new DiffUtil.ItemCallback<MediaItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull MediaItem oldItem, @NonNull MediaItem newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull MediaItem oldItem, @NonNull MediaItem newItem) {
            return false;
        }
    };

    protected MediaItemListAdapter() {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_folder, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MediaItem item = getItem(position);
        holder.title.setText(item.mediaMetadata.title);
        holder.subtitle.setText(item.mediaMetadata.subtitle);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subtitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PlayerActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
