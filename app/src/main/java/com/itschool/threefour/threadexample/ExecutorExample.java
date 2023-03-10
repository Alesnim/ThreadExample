package com.itschool.threefour.threadexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample extends AppCompatActivity {
    Button bt, bt_wrk;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executor_example);

        bt = findViewById(R.id.bt_one);
        img = findViewById(R.id.iv_img);
        bt_wrk = findViewById(R.id.bt_work);
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


        bt_wrk.setOnClickListener((v) -> {

            OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(ExampleWorker.class).build();

            WorkManager.getInstance(getApplicationContext()).enqueue(work);

            WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(work.getId())
                    .observe(this, workInfo -> {
                        if (workInfo != null) {
                            Log.d("TAG", "WorkInfo received: state: " + workInfo.getState());
                            Toast.makeText(this, "Work finished", Toast.LENGTH_SHORT).show();
                        }
                    });


        });


    }
}



