package com.chengfu.android.media.demo.util;

import android.content.Context;

import androidx.annotation.NonNull;

import com.chengfu.android.media.demo.bean.MediaGroup;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MediaUtil {

    public static List<MediaGroup> getMediaGroupList(@NonNull Context context) {
        return new Gson().fromJson(getMediaGroupListJson(context), new TypeToken<List<MediaGroup>>() {
        }.getType());
    }

    public static String getMediaGroupListJson(@NonNull Context context) {
        InputStream inputStream = null;
        String media_json = null;
        try {
            inputStream = context.getAssets().open("medias.json");
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            media_json = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return media_json;
    }
}
