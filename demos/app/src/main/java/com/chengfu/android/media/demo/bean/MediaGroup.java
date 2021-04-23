package com.chengfu.android.media.demo.bean;

import java.io.Serializable;
import java.util.List;

public class MediaGroup implements Serializable {
    private String name;
    private String description;
    private List<MediaItem> mediaItems;

    public MediaGroup() {
    }

    public MediaGroup(String name, String description, List<MediaItem> mediaItems) {
        this.name = name;
        this.description = description;
        this.mediaItems = mediaItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MediaItem> getMediaItems() {
        return mediaItems;
    }

    public void setMediaItems(List<MediaItem> mediaItems) {
        this.mediaItems = mediaItems;
    }

    @Override
    public String toString() {
        return "MediaGroup{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", mediaItems=" + mediaItems +
                '}';
    }
}
