package com.chengfu.android.media.demo;

import android.util.Log;

import java.io.Closeable;
import java.io.IOException;

public class Test implements Closeable {

    public Test() {
        Log.e("fff", "Test");
    }

    @Override
    public void close() throws IOException {
        Log.e("fff", "Test close");
    }
}
