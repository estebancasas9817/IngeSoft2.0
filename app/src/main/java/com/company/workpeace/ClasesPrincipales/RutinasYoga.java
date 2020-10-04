package com.company.workpeace.ClasesPrincipales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.company.workpeace.ClasesAuxiliares.Firebase.Rutinas;
import com.company.workpeace.R;

public class RutinasYoga extends AppCompatActivity {
    TextView ejer1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutinas_yoga);


        ejer1 = findViewById(R.id.ejerYoga1);

        ejer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ejerYoga = new Intent(RutinasYoga.this,EjercicioYoga.class);
                startActivity(ejerYoga);
            }
        });
    }

    private void startTimer() {
    }
}