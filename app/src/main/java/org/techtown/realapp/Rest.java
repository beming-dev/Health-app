package org.techtown.realapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Rest extends Activity {
    SaveExercise saveRead = new SaveExercise();

    TimerTask timerTask;
    Timer timer = new Timer();
    MyTimer myTimer;

    ArrayList<Ex> exercise;
    TextView time;
    int restTime;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rest);

        time = findViewById(R.id.textView_time);
        exercise = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_todayEx);

        Intent intent = getIntent();
        int count = intent.getExtras().getInt("count");
        restTime = exercise.get(count).getRestTime();

        myTimer = new MyTimer(restTime * 1000, 1000);
        myTimer.start();

        time = findViewById(R.id.textView_time);
        exercise = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_todayEx);

        Button stopRest = findViewById(R.id.stopRest);
        stopRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    public class MyTimer extends CountDownTimer {
        public MyTimer(long millisInFutere, long countDownInterval){
            super(millisInFutere, countDownInterval);
            startTimerTask();
        }
        @Override
        public void onTick(long l) {
            time.setText(l/1000 + "초");
        }

        @Override
        public void onFinish() {
            time.setText("0초");
        }
    }

    protected void onDestroy()
    {
        timer.cancel();
        super.onDestroy();
    }

    public void clickHandler(View view)
    {
        switch(view.getId())
        {
            case R.id.stopRest:
                finish();
        }
    }

    private void startTimerTask()
    {
        stopTimerTask();

        timerTask = new TimerTask()
        {
            int count = restTime;

            @Override
            public void run()
            {
                count--;
                time.post(new Runnable() {
                    @Override
                    public void run() {
                        time.setText(count + " 초");
                    }
                });

                if(count<1){stopTimerTask();}
            }
        };
        timer.schedule(timerTask,0 ,1000);
    }

    private void stopTimerTask()
    {
        if(timerTask != null)
        {
            timerTask.cancel();
            timerTask = null;
            finish();
        }
    }
}
