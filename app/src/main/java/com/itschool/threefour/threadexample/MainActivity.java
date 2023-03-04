package com.itschool.threefour.threadexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar1;
    Button button_1;
    Button button_2;
    TextView tw_indicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar1 = findViewById(R.id.progres_1);
        button_1 = findViewById(R.id.bt_one);
        button_2 = findViewById(R.id.bt_two);
        tw_indicator = findViewById(R.id.tw_indicator);
        ProgressThread thread =  new ProgressThread();
        button_1.setOnClickListener(v -> {
           thread.execute();
        });
        button_2.setOnClickListener((x) -> {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        });
    }

    private class ProgressThread extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            for (int x = 0; x< 100; x++){
                try{
                    Thread.sleep(100);
                    publishProgress(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            publishProgress(100);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setTw_indicatorText("Ждем");

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setTw_indicatorText("Все!");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            progressBar1.setProgress(values[0]);
        }
    }



    private void setTw_indicatorText(String text){
        tw_indicator.setText(text);
    }
}
