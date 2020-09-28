package com.company.workpeace;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Bundle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class Ejemplo extends AppCompatActivity {
    TextView titlepage, subtitlepage, intropage, subintropage, btnPerfil, fitonetitle, fitonedesc, fittwotitle, fittwodesc, fitthreetitle,
            fitthreedesc, fitfourtitle, fitfourdesc,btnFavoritos,btnReportes;

    String nombre,correo,clave,usuario;

    DatabaseReference reference;

    View divpage;

    LinearLayout fitone, fittwo, fitthree, fitfour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo);
        reference = FirebaseDatabase.getInstance().getReference("Usuarios");

        Intent intent = getIntent();
        usuario = intent.getStringExtra("usuario");
        correo = intent.getStringExtra("email");
        clave = intent.getStringExtra("clave");
        nombre = intent.getStringExtra("nombre");

        titlepage = (TextView) findViewById(R.id.titlepage);
        subtitlepage = (TextView) findViewById(R.id.subtitlepage);
        intropage = (TextView) findViewById(R.id.intropage);
        subintropage = (TextView) findViewById(R.id.subintropage);
        fitonetitle = (TextView) findViewById(R.id.fitonetitle);
        fitonedesc = (TextView) findViewById(R.id.fitonedesc);
        fittwotitle = (TextView) findViewById(R.id.fittwotitle);
        fittwodesc = (TextView) findViewById(R.id.fittwodesc);
        fitthreetitle = (TextView) findViewById(R.id.fitthreetitle);
        fitthreedesc = (TextView) findViewById(R.id.fitthreedesc);
        fitfourtitle = (TextView) findViewById(R.id.fitfourtitle);
        fitfourdesc = (TextView) findViewById(R.id.fitfourdesc);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnFavoritos = findViewById(R.id.btnFavoritos);
        btnReportes = findViewById(R.id.btnReportes);


        fitone = (LinearLayout) findViewById(R.id.fitone);
        fittwo = (LinearLayout) findViewById(R.id.fittwo);
        fitthree = (LinearLayout) findViewById(R.id.fitthree);
        fitfour = (LinearLayout) findViewById(R.id.fitfour);

        divpage = (View) findViewById(R.id.divpage);
        //bgprogress = (View) findViewById(R.id.bgprogress);

        fitone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Ejemplo.this,Exercise.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });
    }


}


