package com.company.workpeace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Probando extends AppCompatActivity {
    Button ejem;
    String usuario,nombre,clave,email;
    TextInputLayout AAAA;
    DatabaseReference reference;
    Button btnEjemplo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probando);
        btnEjemplo = findViewById(R.id.btnEjemplo);
        reference = FirebaseDatabase.getInstance().getReference("Usuarios");
        AAAA = findViewById(R.id.AAAAA);
        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        clave = intent.getStringExtra("clave");
        email = intent.getStringExtra("email");
        nombre = intent.getStringExtra("nombre");
        //Toast.makeText(Probando.this,"Usuario:"+probando,Toast.LENGTH_LONG).show();
        //Toast.makeText(Probando.this,"Duracion:"+dura,Toast.LENGTH_LONG).show();
        //AAAA.getEditText().setText(probando);

        btnEjemplo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent si = new Intent(Probando.this,Perfil.class);
                si.putExtra("usuario",usuario);
                si.putExtra("nombre",nombre);
                si.putExtra("clave",clave);
                si.putExtra("email",email);
                startActivity(si);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}