package com.company.workpeace.ClasesPrincipales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.company.workpeace.R;

public class Exercise extends AppCompatActivity {

    TextView ejer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        ejer1 = findViewById(R.id.ejer1);

        ejer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ejer1 = new Intent(Exercise.this,Ejemplo.class);
                startActivity(ejer1);
            }
        });
    }
}