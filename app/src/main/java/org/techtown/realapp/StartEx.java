package org.techtown.realapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class StartEx extends AppCompatActivity {
    TextView time;
    TextView tip;
    TextView textView_numOfEx;
    TextView textView_numOfSet;
    TextView textView_nameOfEx;
    Button btnStart;
    Button btnStop;
    Button btnNextSet;

    private InterstitialAd mInterstitialAd;

    ArrayList<Ex> todayEx;
    SaveExercise saveRead = new SaveExercise();

    TimerTask timerTask;
    Timer timer = new Timer();
    MyTimer myTimer;

    int numOfSet;
    int todayEx_num;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start_ex);

        MobileAds.initialize(StartEx.this, getString(R.string.admob_app_id));
        //ad
        mInterstitialAd = new InterstitialAd(getApplicationContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        myTimer = new MyTimer(60000, 1000);

        time = findViewById(R.id.time);
        tip = findViewById(R.id.tip);
        textView_nameOfEx = findViewById(R.id.textView_nameOfEx);
        textView_numOfEx = findViewById(R.id.textView_numOfEx);
        textView_numOfSet = findViewById(R.id.textView_numOfSet);

        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
        btnNextSet = findViewById(R.id.btn_nextSet);
        Button btn_prev = findViewById(R.id.btn_prev);
        Button btn_next = findViewById(R.id.btn_next);

        todayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_todayEx);
        todayEx_num = 0;

        textView_numOfSet.setText("1세트 "+"(총" +todayEx.get(todayEx_num).getSet() + "세트)");
        textView_numOfEx.setText(todayEx.get(todayEx_num).getNumber_of_times() + "회");
        textView_nameOfEx.setText(todayEx.get(todayEx_num).getName() + "");

        numOfSet = 0;

        btnNextSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numOfSet++;
                if(numOfSet != todayEx.get(todayEx_num).getSet()) {
                    time.setText("0 초");
                    textView_numOfSet.setText(numOfSet + 1 + "세트 " + "(총" + todayEx.get(todayEx_num).getSet() + "세트)");
                }

                btnStart.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.GONE);
                btnNextSet.setVisibility(View.GONE);

                if(numOfSet == todayEx.get(todayEx_num).getSet()){
                    Toast.makeText(getApplicationContext(), "마지막 세트입니다.", Toast.LENGTH_LONG).show();
                    numOfSet--;
                    btnStart.setVisibility(View.GONE);
                    btnStop.setVisibility(View.GONE);
                    btnNextSet.setVisibility(View.VISIBLE);
                }
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimerTask();
                time.setText("0 초");
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                todayEx_num++;
                numOfSet = 0;

                btnStart.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.GONE);
                btnNextSet.setVisibility(View.GONE);

                if(todayEx_num >= todayEx.size()-1){
                    showEndMessage();
                    todayEx_num = todayEx.size() - 2;
                } else {
                    textView_numOfSet.setText(numOfSet + 1 + "세트 " + "(총" + todayEx.get(todayEx_num).getSet() + "세트)");
                    textView_numOfEx.setText("세트당" + todayEx.get(todayEx_num).getNumber_of_times() + "회");
                    textView_nameOfEx.setText(todayEx.get(todayEx_num).getName() + "");
                }
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimerTask();
                time.setText("0 초");
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                numOfSet = 0;
                todayEx_num--;

                btnStart.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.GONE);
                btnNextSet.setVisibility(View.GONE);

                if(todayEx_num < 0){
                    Toast.makeText(getApplicationContext(), "이전 운동이 없습니다.", Toast.LENGTH_SHORT).show();
                    todayEx_num = 0;
                } else {
                    textView_numOfSet.setText(numOfSet + 1 + "세트 " + "(총" + todayEx.get(todayEx_num).getSet() + "세트)");
                    textView_numOfEx.setText("세트당" + todayEx.get(todayEx_num).getNumber_of_times() + "회");
                    textView_nameOfEx.setText(todayEx.get(todayEx_num).getName() + "");
                }
            }
        });
    }

    //타이머
    public class MyTimer extends CountDownTimer {
        public MyTimer(long millisInFutere, long countDownInterval){
            super(millisInFutere, countDownInterval);
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
            case R.id.btn_start:
                startTimerTask();
                btnStart.setVisibility(View.GONE);
                btnStop.setVisibility(View.VISIBLE);
                btnNextSet.setVisibility(View.GONE);
                break;
            case R.id.btn_stop :
                stopTimerTask();
                Intent intent = new Intent(getApplicationContext(), Rest.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra("count", todayEx_num);
                startActivity(intent);
                btnStart.setVisibility(View.GONE);
                btnStop.setVisibility(View.GONE);
                btnNextSet.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void startTimerTask()
    {
        stopTimerTask();

        timerTask = new TimerTask()
        {
            int count = 0;

            @Override
            public void run()
            {
                count++;
                time.post(new Runnable() {
                    @Override
                    public void run() {
                        time.setText(count + " 초");
                    }
                });
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
        }
    }

    //팝업
    private void showEndMessage() {
        ArrayList<CompleteEx> CompleteEx;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.FinishExercise));
        builder.setMessage(getString(R.string.FinishExerciseAsk));
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        day = Integer.parseInt(todayEx.get(todayEx.size()-1).getName());

        CompleteEx = saveRead.ReadExercisCompData(getApplicationContext(), Constants.EX_SHP_KEY_COMPLETE);
        if(CompleteEx == null){
            CompleteEx = new ArrayList<>();
        }else{
            if(CompleteEx.get(CompleteEx.size()-1).getDate().equals(day)){
                CompleteEx.remove(CompleteEx.size()-1);
            }
        }
        CompleteEx.add(new CompleteEx(CalendarDay.today(), day));
        saveRead.SaveExerciseCompData(getApplicationContext(), CompleteEx, Constants.EX_SHP_KEY_COMPLETE);

        builder.setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                day = Integer.parseInt(todayEx.get(todayEx.size()-1).getName()) + 1;
                if(day == 5){day =1;}

                switch (day){
                    case 1:
                        todayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1);
                        break;
                    case 2:
                        todayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day2);
                        break;
                    case 3:
                        todayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day3);
                        break;
                    case 4:
                        todayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day4);
                        break;
                }
                if(isExEmpty(todayEx) == 1){
                    todayEx = saveRead.ReadExerciseData(getApplicationContext(), Constants.EX_SHP_KEY_day1);
                    day = 1;
                }

                todayEx.add(new Ex(day + ""));
                saveRead.SaveExerciseData(getApplicationContext(), todayEx, Constants.EX_SHP_KEY_todayEx);

                Intent intent = new Intent(getApplicationContext(), EndEx.class);
                startActivity(intent);

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        builder.setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    int isExEmpty(ArrayList<Ex> exercise) {
        int cnt = 0;
        for (int i = 0; i < exercise.size(); i++) {
            if (exercise.get(i).getChoosed() == 0) {
                cnt++;
            }
        }
        if (cnt == exercise.size()) {
            return 1;
        }
        return 0;
    }
}
