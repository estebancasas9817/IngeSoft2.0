package com.company.workpeace;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Registro extends AppCompatActivity {
    // DECLARACION DE VARIABLES PARA EL REGISTRO DEL USUARIO
    TextInputLayout nUsuario;
    TextInputLayout nombre;
    TextInputLayout email;
    TextInputLayout clave;
    RadioButton radioGeneroF;
    RadioButton radioGeneroM;
    TextInputLayout edad;
    TextInputLayout estatura;
    TextInputLayout peso;
    Button btnRegistrarse;
    Button btnLogin;
    FirebaseDatabase ruta;
    DatabaseReference referencia;
    FirebaseAuth autenticacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // GUARDAR EN LAS VARIABLES LOS DATOS DEL USUARIO
        nUsuario = findViewById(R.id.usuarioRegistro);
        nombre = findViewById(R.id.nombreRegistro);
        email = findViewById(R.id.emailRegistro);
        clave = findViewById(R.id.claveRegistro);
        radioGeneroF = (RadioButton)findViewById(R.id.radioF);
        radioGeneroM = (RadioButton)findViewById(R.id.radioM);
        edad = findViewById(R.id.edad);
        estatura = findViewById(R.id.estatura);
        peso = findViewById(R.id.peso);
        btnRegistrarse = findViewById(R.id.btnRegistro);
        autenticacion = FirebaseAuth.getInstance();
        ruta = FirebaseDatabase.getInstance();
        //Apuntar a la ruta "Usuarios"
        referencia = ruta.getReference("Usuarios");
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // PASAR LAS VARIABLES A SU CORRESPONDIENTE TIPO DE DATOS
                String genero = "";
                String user = nUsuario.getEditText().getText().toString();
                String nombres =nombre.getEditText().getText().toString();
                String mail =email.getEditText().getText().toString();
                String contrasenia =clave.getEditText().getText().toString();
                String age =edad.getEditText().getText().toString();

                //Pasar el dato a entero
                int finalAge=Integer.parseInt(age);
                String height =estatura.getEditText().getText().toString();
                //Pasar el dato a flotante
                float finalHeight = Float.parseFloat(height);
                String kg =peso.getEditText().getText().toString();
                //Pasar el dato a flotante
                float finalPeso=Float.parseFloat(kg);

                if(radioGeneroF.isChecked()){
                    genero = "Femenino";
                }

                if(radioGeneroM.isChecked()){
                    genero = "Masculino";
                }

                //Verificar que no esté vacio el usuario
                if(TextUtils.isEmpty(user)){
                    Toast.makeText(Registro.this,"Ingresa el  usuario",Toast.LENGTH_LONG).show();
                    return;
                }
                //Verificar que no esté vacio el nombre
                if(TextUtils.isEmpty(nombres)){
                    Toast.makeText(Registro.this,"Ingresa el Nombre Completo",Toast.LENGTH_LONG).show();
                    return;
                }
                //Verificar que no esté vacio la email
                if(TextUtils.isEmpty(mail)){
                    Toast.makeText(Registro.this,"Ingresa el email",Toast.LENGTH_LONG).show();
                    return;
                }
                // Verificar que no esté vacio la clave
                if(TextUtils.isEmpty(contrasenia)){
                    Toast.makeText(Registro.this,"Ingresa la clave",Toast.LENGTH_LONG).show();
                    return;
                }
                //Verificar que no esté vacio la edad
                if(TextUtils.isEmpty(age)){
                    Toast.makeText(Registro.this,"Ingresa la edad",Toast.LENGTH_LONG).show();
                    return;
                }
                //Verificar que no esté vacio la estatura
                if(TextUtils.isEmpty(height)){
                    Toast.makeText(Registro.this,"Ingresa la estatura en metros",Toast.LENGTH_LONG).show();
                    return;
                }
                //Verificar que no esté vacio el peso
                if(TextUtils.isEmpty(kg)){
                    Toast.makeText(Registro.this,"Ingresa el peso en kilogramos",Toast.LENGTH_LONG).show();
                    return;
                }
                // ALMACENAR DATOS ANTERIORMENTE GUARDADOS EN LA BASE DE DATOS, USANDO LA CLASE USUARIOSAUX
                UsuariosAux aux = new UsuariosAux(user,nombres,mail,contrasenia,genero,finalAge, finalHeight,finalPeso);
                // DATOS QUEDAN GUARDADOS EN LA RUTA "Usuarios/usuario(nombre del usuario)"
                referencia.child(user).setValue(aux);

                //AUTENTICACION DE USUARIOS NUEVOS

                autenticacion.createUserWithEmailAndPassword(mail, contrasenia)
                        .addOnCompleteListener(Registro.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Si los que ingresó es correcto, se registra y se va a pantalla perfil
                                if (task.isSuccessful()) {
                                    Toast.makeText(Registro.this,"Se ha registrado satisfactoriamente....",Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(Registro.this,Perfil.class);
                                    startActivity(i);
                                } else {
                                    //Si el usuario que se va a registrar ya existe en la base de datos
                                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                        Toast.makeText(Registro.this,"Ese usuario ya existe",Toast.LENGTH_SHORT).show();
                                    }

                                    else{
                                        Toast.makeText(Registro.this,"No se pudo registrar el usuario",Toast.LENGTH_LONG).show();
                                    }


                                }

                            }
                        });

            }
        });
        // PARA DEVOLVERSE A LA PANTALLA LOGIN
        btnLogin = findViewById(R.id.btnPantallaLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Registro.this,Login.class);
                startActivity(next);
            }
        });
    }
}

