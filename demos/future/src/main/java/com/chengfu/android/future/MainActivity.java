package com.chengfu.android.future;
import androidx.appcompat.app.AppCompatActivity;
import androidx.concurrent.futures.ResolvableFuture;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Future<?> f = Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                Log.e("ttt","3333333333333333333");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"kkkkkkkkkkkk");

        try {
            Log.e("ttt","111111111111");
            Log.e("ttt","111111111111"+f.isCancelled());
            Object o = f.get();
            Log.e("ttt","111111111111"+f.isCancelled());
            Log.e("ttt","22222222222222222 o="+o);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("RestrictedApi")
    @SuppressWarnings("WeakerAccess") /* synthetic access */
    ResolvableFuture<String> createFutureForClosed() {
        ResolvableFuture<String> future = ResolvableFuture.create();
        future.set("");
        return future;
    }
}