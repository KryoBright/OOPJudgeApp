package com.example.oopjudgeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ActivityLauncher {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tournament newTour=new Tournament();
        newTour.setName("Some tour");
        newTour.setFinished(false);
        LinearLayoutManager viewManager =new LinearLayoutManager(this);
        TournamentAdapter viewAdapter = new TournamentAdapter(EverythingHolder.getAllTournaments(),this,this);
        RecyclerView recyclerView = findViewById(R.id.TournamentList);
        recyclerView.setLayoutManager(viewManager);
        recyclerView.setAdapter(viewAdapter);
    }


    @Override
    public void startnext(Intent intent) {
        startActivity(intent);
        finish();
    }
}
