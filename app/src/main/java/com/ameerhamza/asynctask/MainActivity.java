package com.ameerhamza.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar mProgressBar;
    Button startBtn;
    TextView progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.mProgressBar);
        startBtn = findViewById(R.id.startBtn);
        progress = findViewById(R.id.progress_value);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                new MyAsyncTask().execute();
            }
        });
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Integer> {

        //This is the first method that is start
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setMax(100);
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (values.length > 0) {
                mProgressBar.setProgress(values[0]);
                progress.setText(String.valueOf(values[0]));
            }
        }

        //This will be the second method that will start
        //We are going to perform time taking tasks in it
        @Override
        protected Integer doInBackground(Void... voids) {
            for (int i = 0; i < 100; i++) {
                publishProgress(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        //This will be the last method that will called when the Task is finished
        @Override
        protected void onPostExecute(Integer aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_LONG).show();
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

}
