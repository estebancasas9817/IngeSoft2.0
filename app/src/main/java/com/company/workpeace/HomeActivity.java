package com.company.workpeace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {
    Button btnHome,btnFavoritos,btnReportes,btnPerfil;
    ImageButton ejerciciosButton, yogaButton,meditacionButton;
    String nombre,correo,clave,usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //obtiene la instancia del intent que lo invocó
        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        correo = intent.getStringExtra("email");
        clave = intent.getStringExtra("clave");
        nombre = intent.getStringExtra("nombre");

        btnHome = findViewById(R.id.btnHome);
        btnFavoritos = findViewById(R.id.btnFavoritos);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnReportes = findViewById(R.id.btnReportes);

        ejerciciosButton = findViewById(R.id.ejerciciosButton);
        yogaButton = findViewById(R.id.yogaButton);
        meditacionButton = findViewById(R.id.meditacionButton);

        //onClickListener de la rutina ejercicios
        ejerciciosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent si = new Intent(HomeActivity.this,EjerciciosActivity.class);
                // PASAR INFO ENTRE ACTIVIDADES
                si.putExtra("usuario",usuario);
                si.putExtra("nombre",nombre);
                si.putExtra("clave",clave);
                si.putExtra("email",correo);
                startActivity(si);
            }
        });

        //onClickListener del boton de perfil
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent si = new Intent(HomeActivity.this,Perfil.class);
                // PASAR INFO ENTRE ACTIVIDADES
                si.putExtra("usuario",usuario);
                si.putExtra("nombre",nombre);
                si.putExtra("clave",clave);
                si.putExtra("email",correo);
                startActivity(si);
            }
        });

    }
}