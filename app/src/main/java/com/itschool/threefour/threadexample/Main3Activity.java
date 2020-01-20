package com.itschool.threefour.threadexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class Main3Activity extends AppCompatActivity {
    ProgressBar progressBar;
    Button button_1;
    TextView tw_progress;
    int progr=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        progressBar = findViewById(R.id.progres_1);
        button_1 = findViewById(R.id.button_1);
        tw_progress = findViewById(R.id.tw_progress);

        @SuppressLint("HandlerLeak") Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                progressBar.setProgress(msg.what);
                Log.d("TAG", String.valueOf(msg.what));
                tw_progress.setText("Текущее значение: " + msg.what);
            }
        };


        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread myThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 100; i++) {
                            try {
                                Thread.sleep(100);
                                handler.sendEmptyMessage(i);

                            } catch (InterruptedException e) {
                                e.printStackTrace();

                            }
                        }
                    }
                });
                myThread.start();


            }
        });




 }


    private void showRes(){
        Toast.makeText(getApplicationContext(), "Дело сделано! ", Toast.LENGTH_SHORT);
    }
}
