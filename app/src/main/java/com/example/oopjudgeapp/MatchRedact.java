package com.example.oopjudgeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MatchRedact extends AppCompatActivity implements ActivityLauncher {

    Integer team1Code;
    Integer team2Code;
    TeamListAdapter viewAdapter2;
    TeamListAdapter viewAdapter1;
    Integer fID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_redact_layout);
        Intent intento=getIntent();
        final Integer id=intento.getIntExtra("id",-1);
        fID=intento.getIntExtra("father",-1);
        Match thisMatch;
        if (id!=-1)
        {
            thisMatch=EverythingHolder.getAllMatches().get(id);
            ((EditText)findViewById(R.id.editTextMatchDate)).setText(EverythingHolder.getAllMatches().get(id).getMatchDate().toString());
            ((EditText)findViewById(R.id.editTextTeam1Score)).setText(EverythingHolder.getAllMatches().get(id).getLeftScore().toString());
            ((EditText)findViewById(R.id.editTextTeam2Score)).setText(EverythingHolder.getAllMatches().get(id).getRightScore().toString());
        }
        else thisMatch=new Match();

        LinearLayoutManager viewManager1 =new LinearLayoutManager(this);

        if (thisMatch.getTeam1()!=null)
        {
            team1Code=thisMatch.getTeam1().getId();
        }
        else team1Code=0;
        viewAdapter1 = new TeamListAdapter(EverythingHolder.getAllTeams(),team1Code,this,this);
        RecyclerView recyclerView1= findViewById(R.id.recyclerTeam1);
        recyclerView1.setLayoutManager(viewManager1);
        recyclerView1.setAdapter(viewAdapter1);
        LinearLayoutManager viewManager2 =new LinearLayoutManager(this);

        if (thisMatch.getTeam2()!=null)
        {
            team2Code=thisMatch.getTeam2().getId();
        }
        else team2Code=0;
        viewAdapter2 = new TeamListAdapter(EverythingHolder.getAllTeams(),team2Code,this,this);
        RecyclerView recyclerView2= findViewById(R.id.recyclerTeam2);
        recyclerView2.setLayoutManager(viewManager2);
        recyclerView2.setAdapter(viewAdapter2);
        ((Button)findViewById(R.id.buttonSaveMatch)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Match newMatch=new Match();
                newMatch.setId(id);
                String dateTxt=((EditText)findViewById(R.id.editTextMatchDate)).getText().toString();
                try {
                    newMatch.setMatchDate(new SimpleDateFormat("dd/MM/yyyy").parse(dateTxt));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                newMatch.setLeftScore(Integer.valueOf(((EditText)findViewById(R.id.editTextTeam1Score)).getText().toString()));
                newMatch.setRightScore(Integer.valueOf(((EditText)findViewById(R.id.editTextTeam2Score)).getText().toString()));
                newMatch.setTeam1(EverythingHolder.getAllTeams().get(viewAdapter1.getChosenID()));
                newMatch.setTeam2(EverythingHolder.getAllTeams().get(viewAdapter2.getChosenID()));
                EverythingHolder.getAllTournaments().get(fID).addMatch(newMatch,EverythingHolder.addMatch(newMatch));
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
        viewAdapter1 =new TeamListAdapter(EverythingHolder.getAllTeams(),team1Code,this,this);
        ((RecyclerView) findViewById(R.id.recyclerTeam1)).swapAdapter(viewAdapter1,false);
        viewAdapter2 =new TeamListAdapter(EverythingHolder.getAllTeams(),team2Code,this,this);
        ((RecyclerView) findViewById(R.id.recyclerTeam2)).swapAdapter(viewAdapter2,false);
    }
}
