package com.company.workpeace;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {
    TextInputLayout usuario;
    TextInputLayout psw;
    Button btnRegistro;
    Button btnIniciarSesion;
    DatabaseReference referencia;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnRegistro = findViewById(R.id.btnPantallaRegistro);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesionLogin);
        usuario = findViewById(R.id.usuarioLogin);
        psw = findViewById(R.id.claveLogin);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // PASAR LAS VARIABLES A SU CORRESPONDIENTE TIPO DE DATOS
                final String usuarioIngreso = usuario.getEditText().getText().toString().trim();
                final String claveIngreso = psw.getEditText().getText().toString().trim();


                //Verificar que no esté vacio el usuario
                if(TextUtils.isEmpty(usuarioIngreso)){
                    Toast.makeText(Login.this,"Ingresa el  usuario",Toast.LENGTH_LONG).show();
                    return;
                }

                //Verificar que no esté vacio la clave
                if(TextUtils.isEmpty(claveIngreso)){
                    Toast.makeText(Login.this,"Ingresa la clave",Toast.LENGTH_LONG).show();
                    return;
                }


                //Apunto a la ruta "Usuarios"
                referencia = FirebaseDatabase.getInstance().getReference("Usuarios");

                // Quedar apuntando al hijo de la ruta "Usuarios"
                Query checkUsuario = referencia.orderByChild("usuario").equalTo(usuarioIngreso);

                checkUsuario.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // si el usuario existe

                        if(dataSnapshot.exists()){
                            usuario.setError(null);
                            usuario.setErrorEnabled(false);
                            String claveBD = dataSnapshot.child(usuarioIngreso).child("clave").getValue(String.class);

                            //Si los datos que ingresó son los mismos que los de la base de datos, paso a la siguiente pantalla
                            if(claveBD.equals(claveIngreso)){
                                usuario.setError(null);
                                usuario.setErrorEnabled(false);
                                String usuarioBD = dataSnapshot.child(usuarioIngreso).child("usuario").getValue(String.class);
                                String emailDB = dataSnapshot.child(usuarioIngreso).child("email").getValue(String.class);
                                String nombreDB = dataSnapshot.child(usuarioIngreso).child("nombre").getValue(String.class);

                                Toast.makeText(Login.this,"Bienvenido de vuelta",Toast.LENGTH_SHORT).show();

                                Intent sig = new Intent(Login.this,HomeActivity.class);
                                //Guardar el usuario para mostrar los datos de ese usuario en otra pantalla y solo los de ese usuario
                                sig.putExtra("usuario",usuarioBD);
                                sig.putExtra("email",emailDB);
                                sig.putExtra("nombre",nombreDB);
                                sig.putExtra("clave",claveBD);
                                //Pasar a la pantalla Pefil
                                startActivity(sig);

                            }else{
                                // Si la clave que ingresó no es igual a la de la base datos
                                psw.setError("Contraseña incorrecta");
                                psw.requestFocus();
                            }


                        }else{
                            // Si el usuario no existe
                            usuario.setError("Ese usuario no existe");
                            usuario.requestFocus();
                        }

                    }




                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });






        //CAMBIAR A PANTALLA REGISTRO
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente = new Intent(Login.this,Registro.class);
                startActivity(siguiente);
            }
        });

    }
}


