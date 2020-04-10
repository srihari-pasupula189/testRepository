package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2;
    private ImageView imageView1, imageView2;
    ServiceWorker serviceWorker1 = new ServiceWorker("serviceworker_1");
    ServiceWorker serviceWorker2 = new ServiceWorker("serviceworker_2");
    public static final String IMAGE_1 = "https://www.online-image-editor.com/online-image-editor-logo.png"; // url of first image
    public static final String IMAGE_2 = "https://miro.medium.com/max/1400/1*23llPqkcFUqO7KKJCHNGRw.png"; // url of second image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        imageView1 = findViewById(R.id.image1);
        imageView2 = findViewById(R.id.image2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchImage1AndSet();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchImage2AndSet();
            }
        });

    }

    private void fetchImage1AndSet() {
        final OkHttpClient okHttpClient = Networking.getInstance();
        serviceWorker1.addTask(new Task<Bitmap>() {
            @Override
            public Bitmap onExecute() {
                Request request = new Request.Builder().url(IMAGE_1).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    return bitmap;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void onComplete(Bitmap result) {
                if (result != null) {
                    imageView1.setImageBitmap(result);
                }
            }
        });
    }

    private void fetchImage2AndSet() {
        final OkHttpClient okHttpClient = Networking.getInstance();
        serviceWorker2.addTask(new Task<Bitmap>() {
            @Override
            public Bitmap onExecute() {
                Request request = new Request.Builder().url(IMAGE_2).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    return bitmap;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void onComplete(Bitmap result) {
                if (result != null) {
                    imageView2.setImageBitmap(result);
                }
            }
        });
    }
}
