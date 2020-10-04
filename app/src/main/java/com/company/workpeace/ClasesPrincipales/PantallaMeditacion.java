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

public class PantallaMeditacion extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView menuIcon;
    TextView meditacion;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pantalla_meditacion);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        meditacion = findViewById(R.id.medTitulo);
        menuIcon = findViewById(R.id.menu_icon);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        navigationDrawer();

        meditacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent meditation = new Intent(PantallaMeditacion.this,RutinasYoga.class);
                startActivity(meditation);
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
                Intent si = new Intent(PantallaMeditacion.this,Perfil.class);
                si.putExtra("usuario",usuario);
                si.putExtra("nombre",nombre);
                si.putExtra("clave",clave);
                si.putExtra("email",correo);
                startActivity(si);
                break;
            case R.id.nav_peso_y_medidas:
                Intent peso = new Intent(PantallaMeditacion.this,Probando.class);
                startActivity(peso);
                break;
            case R.id.nav_home:
                Intent home = new Intent(PantallaMeditacion.this,Init.class);
                home.putExtra("usuario",usuario);
                home.putExtra("nombre",nombre);
                home.putExtra("clave",clave);
                home.putExtra("email",correo);
                startActivity(home);
                break;
            case R.id.nav_yoga:
                Intent yoga = new Intent(PantallaMeditacion.this,Yoga.class);
                yoga.putExtra("usuario",usuario);
                yoga.putExtra("nombre",nombre);
                yoga.putExtra("clave",clave);
                yoga.putExtra("email",correo);
                startActivity(yoga);
                break;
        }
        return true;

    }
}
