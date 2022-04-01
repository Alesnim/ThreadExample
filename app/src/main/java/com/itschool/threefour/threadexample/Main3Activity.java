package com.itschool.threefour.threadexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    ProgressBar progressBar;
    Button button_1, button_2;
    TextView tw_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        progressBar = findViewById(R.id.progres_1);
        button_1 = findViewById(R.id.bt_one);
        tw_progress = findViewById(R.id.tw_progress);
        button_2 = findViewById(R.id.bt_two);

        @SuppressLint("HandlerLeak") Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                progressBar.setProgress(msg.what);

                Log.d("TAG", String.valueOf(msg.what));
                tw_progress.setText("Текущее значение: " + msg.what);
            }
        };

        button_1.setOnClickListener(v -> {
            Thread myThread = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(100);
                        handler.sendEmptyMessage(i);

                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
                new Handler(Looper.getMainLooper()).post(this::showRes);
            });
            myThread.start();


        });

        button_2.setOnClickListener(view -> {
            startActivity(new Intent(this, ExecutorExample.class));
        });


    }


    private void showRes() {
        Toast.makeText(getApplicationContext(), "Дело сделано! ", Toast.LENGTH_SHORT).show();
    }
}
