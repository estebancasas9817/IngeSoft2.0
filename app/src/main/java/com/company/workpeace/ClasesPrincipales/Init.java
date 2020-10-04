package com.company.workpeace.ClasesPrincipales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.company.workpeace.R;
import com.google.android.material.navigation.NavigationView;

public class Init extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView menuIcon;
    TextView rutina1;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_init);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        rutina1 = findViewById(R.id.fitUnoTitulo);
        menuIcon = findViewById(R.id.menu_icon);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        navigationDrawer();


        rutina1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent Rutina1 = new Intent(Init.this,Exercise.class);
                startActivity(Rutina1);
            }
        });

        progressBar.setVisibility(View.GONE);
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        String usuario,correo,clave,nombre;

        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        correo = intent.getStringExtra("email");
        clave = intent.getStringExtra("clave");
        nombre = intent.getStringExtra("nombre");

        switch (menuItem.getItemId()){

            case R.id.nav_perfil:
                Intent perfil = new Intent(Init.this,Perfil.class);
                perfil.putExtra("usuario",usuario);
                perfil.putExtra("nombre",nombre);
                perfil.putExtra("clave",clave);
                perfil.putExtra("email",correo);

                startActivity(perfil);
                break;
            case R.id.nav_yoga:
                Intent yoga = new Intent(Init.this,Yoga.class);
                yoga.putExtra("usuario",usuario);
                yoga.putExtra("nombre",nombre);
                yoga.putExtra("clave",clave);
                yoga.putExtra("email",correo);
                startActivity(yoga);
                break;
            case R.id.nav_peso_y_medidas:
                Intent peso = new Intent(Init.this,Probando.class);
                startActivity(peso);
                break;
            case R.id.nav_meditacion:
                Intent meditacion = new Intent(Init.this,PantallaMeditacion.class);
                meditacion.putExtra("usuario",usuario);
                meditacion.putExtra("nombre",nombre);
                meditacion.putExtra("clave",clave);
                meditacion.putExtra("email",correo);
                startActivity(meditacion);
                break;
        }
        return true;

    }
}