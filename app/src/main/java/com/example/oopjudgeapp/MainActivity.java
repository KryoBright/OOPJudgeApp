package com.example.oopjudgeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements ActivityLauncher {

    TournamentAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int i=0;
        while (i<10)
        {
            EverythingHolder.addTrainer(new Trainer(UUID.randomUUID().toString().substring(0,8)));
            i++;
        }
        Tournament newTour=new Tournament();
        newTour.setName("Some tour");
        newTour.setFinished(false);
        LinearLayoutManager viewManager =new LinearLayoutManager(this);
        viewAdapter = new TournamentAdapter(EverythingHolder.getAllTournaments(),this,this);
        RecyclerView recyclerView = findViewById(R.id.TournamentList);
        recyclerView.setLayoutManager(viewManager);
        recyclerView.setAdapter(viewAdapter);
    }


    @Override
    public void startnext(Intent intent) {
        startActivity(intent);
    }

    protected void onResume(){
        super.onResume();
        viewAdapter = new TournamentAdapter(EverythingHolder.getAllTournaments(),this,this);
        RecyclerView recyclerView = findViewById(R.id.TournamentList);
        recyclerView.setAdapter(viewAdapter);
        int i=0;
        while (i<EverythingHolder.getAllTournaments().size())
        {
            EverythingHolder.getAllTournaments().get(i).updateStatus(new Date());
            i++;
        }
    }
}
