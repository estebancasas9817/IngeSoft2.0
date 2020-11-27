package com.company.workpeace.ClasesPrincipales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.company.workpeace.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// CLASE QUE PERMITE AL USUARIO VISUALIZAR Y ACTUALIZAR LOS DATOS DE USUARIO.

public class Perfil extends AppCompatActivity {
    TextInputLayout fullName, email,password;
    TextView fullNameLabel, usernameLabel;
    Button btnAct;

    ImageView btn;

    ImageView volver;

    String _USERNAME, _NAME, _EMAIL,_PASSWORD;

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        reference = FirebaseDatabase.getInstance().getReference("Usuarios");

        volver = findViewById(R.id.volverPerfil);

        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        password = findViewById(R.id.password_profile);
        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);
        btnAct = findViewById(R.id.btnActualizar);

        Intent intent = getIntent();
        final String usuarioss, claves, mails,nombres;
        usuarioss = intent.getStringExtra("usuario");
        nombres = intent.getStringExtra("nombre");
        mails = intent.getStringExtra("email");
        claves = intent.getStringExtra("clave");

        showAllUserData();



        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNameChanged() || isPasswordChanged()) {
                    Toast.makeText(Perfil.this,"Los datos se han actualizado satisfactoriamente",Toast.LENGTH_LONG).show();

                }
                else Toast.makeText(Perfil.this, "Los datos son los mismos, no se pudieron actualizar ", Toast.LENGTH_LONG).show();

            }
        });


        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(Perfil.this,Yoga.class);
                volver.putExtra("usuario",usuarioss);
                volver.putExtra("nombre",nombres);
                volver.putExtra("email",mails);
                volver.putExtra("clave",claves);
                startActivity(volver);
            }
        });

    }

    private void showAllUserData() {

        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra("usuario");
        _NAME = intent.getStringExtra("nombre");
        _EMAIL = intent.getStringExtra("email");
        _PASSWORD = intent.getStringExtra("clave");




        fullNameLabel.setText(_NAME);
        usernameLabel.setText(_USERNAME);
        fullName.getEditText().setText(_NAME);
        email.getEditText().setText(_EMAIL);
        password.getEditText().setText(_PASSWORD);
    }
    private boolean isPasswordChanged() {
        if (!_PASSWORD.equals(password.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("clave").setValue(password.getEditText().getText().toString());
            _PASSWORD = password.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isNameChanged() {

        if (!_NAME.equals(fullName.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("nombre").setValue(fullName.getEditText().getText().toString());
            _NAME = fullName.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }

    }
}