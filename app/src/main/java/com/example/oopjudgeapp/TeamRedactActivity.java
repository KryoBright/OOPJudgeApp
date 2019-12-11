package com.example.oopjudgeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TeamRedactActivity extends AppCompatActivity implements ActivityLauncher {

    TeamPlayerAdapter viewAdapter1;
    Team thisTeam;
    List <Boolean> included;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_redact_layout);
        Integer id=getIntent().getIntExtra("id",-1);
        if (id!=-1)
            thisTeam=EverythingHolder.getAllTeams().get(id);
        else thisTeam=new Team();
        final Spinner spinner = findViewById(R.id.spinnerTeamTrainer);
        List<Trainer> data=Collections.EMPTY_LIST;
        Collections.copy(data,EverythingHolder.getAllTrainers());

        included=new ArrayList<>(EverythingHolder.getAllPlayers().size());

        int i=0;
        while (i<EverythingHolder.getAllPlayers().size())
        {
            if (thisTeam.getTeamPlrs().contains(EverythingHolder.getAllPlayers().get(i)))
                included.add(true);
            else included.add(false);
            i++;
        }

        ArrayAdapter<?> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,EverythingHolder.getTrainersAsString());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        LinearLayoutManager viewManager1 =new LinearLayoutManager(this);
        viewAdapter1 = new TeamPlayerAdapter(EverythingHolder.getAllPlayers(),included,this,this);
        RecyclerView recyclerView1= findViewById(R.id.recyclerTeamPlayers);
        recyclerView1.setLayoutManager(viewManager1);
        recyclerView1.setAdapter(viewAdapter1);

        findViewById(R.id.buttonSaveTeam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Team newTeam=new Team();
                newTeam.setId(thisTeam.getId());
                newTeam.setName(((EditText)findViewById(R.id.editTextTeamName)).getText().toString());
                int i=0;
                while (i<included.size())
                {
                    if (included.get(i))
                        newTeam.addPlayer(EverythingHolder.getAllPlayers().get(i));
                    i++;
                }
                EverythingHolder.addTeam(newTeam);
                finish();
            }
        });
    }

    @Override
    public void startnext(Intent intent) {
        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        included=new ArrayList<>(EverythingHolder.getAllPlayers().size());

        int i=0;
        while (i<EverythingHolder.getAllPlayers().size())
        {
            if(i<included.size())
            included.set(i,thisTeam.getTeamPlrs().contains(EverythingHolder.getAllPlayers().get(i)));
            else included.add(thisTeam.getTeamPlrs().contains(EverythingHolder.getAllPlayers().get(i)));
            i++;
        }
        viewAdapter1 =new TeamPlayerAdapter(EverythingHolder.getAllPlayers(),included,this,this);
        viewAdapter1.updData(EverythingHolder.getAllPlayers());
        ((RecyclerView) findViewById(R.id.recyclerTeamPlayers)).swapAdapter(viewAdapter1,false);
    }
}
