package com.example.assignment;

import android.os.Handler;
import android.os.Looper;


public class ServiceWorker<T> {
    private Handler mainHandler;
    private String name;

    public ServiceWorker(String name) {
        mainHandler = new Handler(Looper.getMainLooper());
        this.name = name;
    }

    public void addTask(Task T) {
        this.execute(T);
    }


    private synchronized void execute(Task T) {

        final Task<T> headTask = T;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                T result = null;
                try {
                    result = headTask.onExecute(); // runs on background thread
                } catch (Exception e) {
                } finally {
                    final T finalResult = result;
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            headTask.onComplete(finalResult);
                        }
                    });
                }
            }
        });
        t.start();


    }


}
