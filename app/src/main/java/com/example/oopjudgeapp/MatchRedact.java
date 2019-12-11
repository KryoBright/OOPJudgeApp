package com.example.oopjudgeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MatchRedact extends AppCompatActivity implements ActivityLauncher {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_redact_layout);
        Integer id=getIntent().getIntExtra("id",-1);
        if (id!=-1)
        {
            ((EditText)findViewById(R.id.editTextMatchDate)).setText(EverythingHolder.getAllMatches().get(id).getMatchDate().toString());
            ((EditText)findViewById(R.id.editTextTeam1Score)).setText(EverythingHolder.getAllMatches().get(id).getLeftScore().toString());
            ((EditText)findViewById(R.id.editTextTeam2Score)).setText(EverythingHolder.getAllMatches().get(id).getRightScore().toString());
        }

        LinearLayoutManager viewManager1 =new LinearLayoutManager(this);
        TeamListAdapter viewAdapter1 = new TeamListAdapter(EverythingHolder.getAllTeams(),this,this);
        RecyclerView recyclerView1= findViewById(R.id.recyclerTeam1);
        recyclerView1.setLayoutManager(viewManager1);
        recyclerView1.setAdapter(viewAdapter1);
        LinearLayoutManager viewManager2 =new LinearLayoutManager(this);
        TeamListAdapter viewAdapter2 = new TeamListAdapter(EverythingHolder.getAllTeams(),this,this);
        RecyclerView recyclerView2= findViewById(R.id.recyclerTeam2);
        recyclerView2.setLayoutManager(viewManager2);
        recyclerView2.setAdapter(viewAdapter2);
    }

    @Override
    public void startnext(Intent intent) {
        startActivity(intent);
    }
}
