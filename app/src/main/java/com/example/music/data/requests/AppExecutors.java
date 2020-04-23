package com.example.music.data.requests;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {
    private static AppExecutors instance;

    public static AppExecutors getInstance() {
        if (instance == null)
            instance = new AppExecutors();
        return instance;
    }
//    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);
//    public ScheduledExecutorService getmNetworkIO(){
//        return mNetworkIO;
//    }

    private final Executor mDiskIO = Executors.newSingleThreadExecutor();
    private final Executor mMainThreadExecutor = new MainThreadExecutor();

    public Executor diskIO() {
        return mDiskIO;
    }

    public Executor mainThread() {
        return mMainThreadExecutor;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadhandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mainThreadhandler.post(command);
        }
    }

}
