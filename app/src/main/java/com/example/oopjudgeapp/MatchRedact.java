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

        LinearLayoutManager viewManager =new LinearLayoutManager(this);
        TeamListAdapter viewAdapter = new TeamListAdapter(EverythingHolder.getAllTeams(),this,this);
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
