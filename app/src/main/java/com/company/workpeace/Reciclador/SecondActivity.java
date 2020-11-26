package com.company.workpeace.Reciclador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.company.workpeace.R;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    HelperAdapter2 helperAdapter2;
    RecyclerView recyclerViewSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);




    }
}