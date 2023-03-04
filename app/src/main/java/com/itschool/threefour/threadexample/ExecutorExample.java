package com.itschool.threefour.threadexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample extends AppCompatActivity {
    Button bt;
    ImageView img;
    float alph = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executor_example);

        bt = findViewById(R.id.bt_one);
        img = findViewById(R.id.iv_img);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        bt.setOnClickListener(view -> executorService.execute(() -> {
            float alph = 0f;
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(100);
                    alph += 0.1;
                    float finalAlph = alph;
                    img.post(() -> {
                        img.setAlpha(finalAlph);
                    });

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Handler(Looper.getMainLooper()).post(
                    () -> Toast.makeText(this, "Дело сделано", Toast.LENGTH_SHORT).show());
        }));

    }
}


