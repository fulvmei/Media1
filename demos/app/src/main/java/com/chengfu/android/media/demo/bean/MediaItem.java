package com.chengfu.android.media.demo.bean;

import java.io.Serializable;

public class MediaItem implements Serializable {
    private int type;
    private String name;
    private String uri;

    public MediaItem() {
    }

    public MediaItem(int type, String name, String uri) {
        this.type = type;
        this.name = name;
        this.uri = uri;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "MediaItem{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
