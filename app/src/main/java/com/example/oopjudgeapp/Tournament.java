package com.example.oopjudgeapp;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Tournament extends IDable {
    private List<Match> matches=new ArrayList<>();
    private String name;
    private String desc;
    private boolean finished;

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Tournament(){
        EverythingHolder.addTournament(this);
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Match> getMatches() {
        return Collections.unmodifiableList(matches);
    }

    public void addMatch(Match match){
        matches.add(match);
    }

    public List<Team> getAllTeams()
    {
        List<Team> newlist=new ArrayList<>();
        int i=0;
        while (i<matches.size())
        {
            if (!newlist.contains(matches.get(i).getTeam1()))
            {
                newlist.add(matches.get(i).getTeam1());
            }
            if (!newlist.contains(matches.get(i).getTeam2()))
            {
                newlist.add(matches.get(i).getTeam2());
            }
            i++;
        }
        return newlist;
    }
    public List<Pair<Team,Integer>> getAllTeamsAndScoresSorted()
    {
        List<Team> teams=getAllTeams();
        List<Integer> scores=new ArrayList<>(teams.size());
        int i=0;
        while (i<matches.size())
        {
            if (!(matches.get(i).getWinner() == null))
                scores.set(teams.indexOf(matches.get(i).getWinner()),
                        scores.get(teams.indexOf(matches.get(i).getWinner())) + 1);
            i++;
        }
        boolean sorted=false;
        while (!sorted)
        {
            sorted=true;
            i=0;
            while (i<teams.size()-1)
            {
                if (scores.get(i)<scores.get(i+1))
                {
                    int tmp=scores.get(i);
                    scores.set(i,scores.get(i+1));
                    scores.set(i+1,tmp);
                    Team tmpTeam=teams.get(i);
                    teams.set(i,teams.get(i+1));
                    teams.set(i+1,tmpTeam);
                    sorted=false;
                }
                i++;
            }
        }
        List<Pair<Team,Integer>> toReturn=new ArrayList<>();
        i=0;
        while (i<teams.size())
        {
            toReturn.add(Pair.create(teams.get(i),scores.get(i)));
            i++;
        }
        return toReturn;
    }

    public void updateStatus(Date now)
    {
        boolean status=true;
        Integer i=0;
        while (i<matches.size()){
            if (matches.get(i).getMatchDate().after(now))
            {
                status=false;
            }
            i++;
        }
        if (status)
        {
            finished=true;
        }
    }
}
