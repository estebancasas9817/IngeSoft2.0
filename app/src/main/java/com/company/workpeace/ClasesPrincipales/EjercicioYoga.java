package com.company.workpeace.ClasesPrincipales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.company.workpeace.R;

import java.util.Locale;

public class EjercicioYoga extends AppCompatActivity {
    TextView intropage, subintropage, fitonetitle, fitonedesc, timerValue, btnexercise;
    View divpage, bgprogress;
    LinearLayout fitone;
    ImageView imgTimer;

    private static final long START_TIME_IN_MILLIS = 50000;
    private CountDownTimer countDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_yoga);


        intropage = (TextView) findViewById(R.id.descEjerYoga);
        subintropage = (TextView) findViewById(R.id.subDescEjerYoga);
        fitonetitle = (TextView) findViewById(R.id.nombreEjerYoga);
        fitonedesc = (TextView) findViewById(R.id.seriesEjerYoga);
        timerValue = (TextView) findViewById(R.id.timerYoga);
        btnexercise = (TextView) findViewById(R.id.btnSeguiRutinaYoga);

        divpage = (View) findViewById(R.id.divpage);
        bgprogress = (View) findViewById(R.id.bgprogress);

        fitone = (LinearLayout) findViewById(R.id.linearLayoutYoga);

        imgTimer = (ImageView) findViewById(R.id.imgtimer);


        startTimer();

        btnexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverRutina = new Intent(EjercicioYoga.this, RutinasYoga.class);
                startActivity(volverRutina);
            }
        });
    }


    private void startTimer() {

        countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"Done!", Toast.LENGTH_SHORT).show();
            }
        }.start();
        mTimerRunning = true;
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeft = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds) ;
        timerValue.setText(timeLeft);
    }
}