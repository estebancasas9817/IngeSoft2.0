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

public class Yoga extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView menuIcon;
    TextView rutinaYoga1;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_yoga);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        rutinaYoga1 = findViewById(R.id.yoga1Titulo);
        menuIcon = findViewById(R.id.menu_icon);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        navigationDrawer();

        rutinaYoga1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent RutinaYoga1 = new Intent(Yoga.this,RutinasYoga.class);
                startActivity(RutinaYoga1);
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
                Intent si = new Intent(Yoga.this,Perfil.class);
                si.putExtra("usuario",usuario);
                si.putExtra("nombre",nombre);
                si.putExtra("clave",clave);
                si.putExtra("email",correo);
                startActivity(si);
                break;
            case R.id.nav_peso_y_medidas:
                Intent peso = new Intent(Yoga.this,Probando.class);
                startActivity(peso);
                break;
            case R.id.nav_home:
                Intent home = new Intent(Yoga.this,Init.class);
                home.putExtra("usuario",usuario);
                home.putExtra("nombre",nombre);
                home.putExtra("clave",clave);
                home.putExtra("email",correo);
                startActivity(home);
                break;
            case R.id.nav_meditacion:
                Intent meditacion = new Intent(Yoga.this,PantallaMeditacion.class);
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