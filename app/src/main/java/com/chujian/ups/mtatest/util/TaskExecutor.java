package com.chujian.ups.mtatest.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskExecutor {
    private static final String TAG = TaskExecutor.class.getSimpleName();

    private static final int NORMAL_CORE_POOL_SIZE = Math.max(Runtime.getRuntime().availableProcessors(), 5);
    private static final int NORMAL_MAX_POOL_SIZE = NORMAL_CORE_POOL_SIZE * 2 + 1;
    private static final long NORMAL_KEEP_ALIVE_TIME = 5L;

    private final Object mLock = new Object();
    private static volatile TaskExecutor sInstance;
    private volatile ThreadPoolExecutor mThreadPoolExecutor = null;
    private volatile ScheduledThreadPoolExecutor mScheduledService = null;

    private volatile Handler mMainHandler;

    public static TaskExecutor getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (TaskExecutor.class) {
            if (sInstance == null) {
                sInstance = new TaskExecutor();
            }
        }
        return sInstance;
    }

    public void executeOnScheduleTask(Runnable runnable, long delay) {
        getScheduledService().schedule(runnable, delay, TimeUnit.MILLISECONDS);
    }

    public ScheduledFuture<?> executeOnScheduleTask(Runnable runnable, long delay, TimeUnit unit) {
        return getScheduledService().schedule(runnable, delay, unit);
    }

    public void executeOnThread(Runnable runnable) {
        getThreadService().submit(runnable);
    }

    public void postToMainThread(Runnable runnable) {
        if (mMainHandler == null) {
            synchronized (mLock) {
                if (mMainHandler == null) {
                    mMainHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        //noinspection ConstantConditions
        mMainHandler.post(runnable);
    }

    public void delayPostToMainThread(Runnable runnable, long delayMillis) {
        if (mMainHandler == null) {
            synchronized (mLock) {
                if (mMainHandler == null) {
                    mMainHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        //noinspection ConstantConditions
        mMainHandler.postDelayed(runnable, delayMillis);
    }

    public boolean isMainThread() {
        boolean isMainThread = Looper.getMainLooper().getThread() == Thread.currentThread();
        Log.d(TAG, "TaskExecutor isMainThread:" + isMainThread);
        return isMainThread;
    }

    private boolean isScheduledServiceEnable() {
        return !(mScheduledService == null
                || mScheduledService.isShutdown() || mScheduledService.isTerminated());
    }

    private ScheduledExecutorService getScheduledService() {
        synchronized (TaskExecutor.class) {
            if (!isScheduledServiceEnable()) {
                mScheduledService = new ScheduledThreadPoolExecutor(2, new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "TaskExecutor-111-");
                    }
                });

                mScheduledService.allowCoreThreadTimeOut(true);
            }
            return mScheduledService;
        }
    }

    private boolean isThreadServiceEnable() {
        return !(mThreadPoolExecutor == null
                || mThreadPoolExecutor.isShutdown() || mThreadPoolExecutor.isTerminated());
    }

    private ThreadPoolExecutor getThreadService() {
        synchronized (TaskExecutor.class) {
            if (!isThreadServiceEnable()) {
                mThreadPoolExecutor = new ThreadPoolExecutor(NORMAL_CORE_POOL_SIZE, NORMAL_MAX_POOL_SIZE,
                        NORMAL_KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "TaskExecutor-222-");
                    }
                });

                mThreadPoolExecutor.allowCoreThreadTimeOut(true);
            }
            return mThreadPoolExecutor;
        }
    }
}
