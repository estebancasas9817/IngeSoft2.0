package com.company.workpeace.ClasesPrincipales;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.company.workpeace.R;

import java.util.concurrent.TimeUnit;

public class EjercicioMed extends AppCompatActivity {

    TextView playerPosicion,playerDuration;
    SeekBar seekBar;
    ImageView btrew, btPlay, btPause, btFf;

    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_med);

        playerPosicion = findViewById(R.id.posicion_player);
        playerDuration = findViewById(R.id.duracionMeditacion);
        seekBar = findViewById(R.id.seekBar);
        seekBar = findViewById(R.id.seekBar);
        btrew = findViewById(R.id.bt_rew);
        btPause = findViewById(R.id.bt_pausar);
        btPlay = findViewById(R.id.btPlay);
        btFf = findViewById(R.id.bt_ff);

        mediaPlayer = MediaPlayer.create(this,R.raw.los_natas);

        runnable = new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());

                handler.postDelayed(this,500);
            }
        };
        int duracion = mediaPlayer.getDuration();
        String sDuracion = convertFormat(duracion);

        playerDuration.setText(sDuracion);

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlay.setVisibility(View.GONE);
                btPause.setVisibility(View.VISIBLE);
                mediaPlayer.start();
                seekBar.setMax(mediaPlayer.getDuration());
                handler.postDelayed(runnable,0);
            }
        });



        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPause.setVisibility(View.GONE);
                btPlay.setVisibility(View.VISIBLE);
                mediaPlayer.pause();
                handler.removeCallbacks(runnable);

            }
        });


        btFf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicionActual = mediaPlayer.getCurrentPosition();
                int duracion = mediaPlayer.getDuration();
                if(mediaPlayer.isPlaying() && duracion != posicionActual){
                    posicionActual = posicionActual + 5000;

                    playerPosicion.setText(convertFormat(posicionActual));

                    mediaPlayer.seekTo(posicionActual);

                }

            }
        });

        btrew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicionActual = mediaPlayer.getCurrentPosition();
                if(mediaPlayer.isPlaying() && posicionActual > 5000){
                    posicionActual = posicionActual - 5000;

                    playerPosicion.setText(convertFormat(posicionActual));

                    mediaPlayer.seekTo(posicionActual);

                }

            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }
                playerPosicion.setText(mediaPlayer.getCurrentPosition());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btPause.setVisibility(View.GONE);
                btPlay.setVisibility(View.VISIBLE);
                mediaPlayer.seekTo(0);
            }
        });




    }

    @SuppressLint("DefaultLocale")
    private String convertFormat(int duracion) {


        return String.format("%02d:%02d"
                , TimeUnit.MILLISECONDS.toMinutes(duracion)
                , TimeUnit.MILLISECONDS.toSeconds(duracion)
                , TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duracion)));



    }
}