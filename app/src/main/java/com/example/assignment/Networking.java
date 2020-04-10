package com.example.assignment;

import com.squareup.okhttp.OkHttpClient;

public class Networking {
    private static OkHttpClient okHttpClient;


    public static synchronized OkHttpClient getInstance() {
        if (okHttpClient == null) {
            synchronized (Networking.class) {
                if (okHttpClient == null)
                    okHttpClient = new OkHttpClient();
            }
        }
        return okHttpClient;
    }

}
