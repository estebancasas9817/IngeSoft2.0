package com.company.workpeace.ClasesPrincipales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.company.workpeace.ClasesAuxiliares.Firebase.MiRutinaAux;
import com.company.workpeace.ClasesAuxiliares.Firebase.MyDoesAux;
import com.company.workpeace.R;
import com.company.workpeace.Reciclador.DoesAdapter;
import com.company.workpeace.Reciclador.DoesAdapterRutinas;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Rutinas extends AppCompatActivity {

    TextView titlepageRutina, subtitlepageRutina, endpageRutina;
    Button btnAddNewRutina;

    DatabaseReference reference;
    RecyclerView ourdoesRutina;
    ArrayList<MiRutinaAux> list;
    DoesAdapterRutinas doesAdapterRutina;
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutinas);
        String usuarios;
        Intent intent = getIntent();

        Intent in = getIntent();
        usuarios = in.getStringExtra("usuario");

        usuario = intent.getStringExtra("usuario");

        titlepageRutina = findViewById(R.id.titlepageRutina);
        subtitlepageRutina = findViewById(R.id.subtitlepageRutina);
        endpageRutina = findViewById(R.id.endpageRutina);

        btnAddNewRutina = findViewById(R.id.btnAddNewRutina);


        btnAddNewRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Rutinas.this, RutinasPersonalizadas.class);
                a.putExtra("usuario",usuario);
                startActivity(a);
            }
        });


        ourdoesRutina = findViewById(R.id.ourdoesRutina);
        ourdoesRutina.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MiRutinaAux>();

        // get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("MiRutina").child(usuario);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // set code to retrive data and replace layout
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    MiRutinaAux p = dataSnapshot1.getValue(MiRutinaAux.class);
                    list.add(p);
                }
                doesAdapterRutina = new DoesAdapterRutinas(Rutinas.this, list);
                ourdoesRutina.setAdapter(doesAdapterRutina);
                doesAdapterRutina.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }

        });
    }
}