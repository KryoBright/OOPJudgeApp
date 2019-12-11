package com.example.oopjudgeapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team extends IDable{
    private String name;
    private Trainer trainer=null;
    private List<Player> teamPlrs=new ArrayList<>();
    private Integer wins=0,loses=0;

    public Team(){
        //EverythingHolder.addTeam(this);
    }

    public List<Player> getTeamPlrs(){
        return Collections.unmodifiableList(teamPlrs);
    }

    public void addPlayer(Player player){
        teamPlrs.add(player);
    }

    public void setTrainer(Trainer trainer){
        if (trainer!=null) {
            if (trainer.getTeam() != null) {
                trainer.getTeam().setTrainer(null);
                trainer.setTeam(this);
            }
        }
        this.trainer=trainer;
    }

    public String getName() {
        return name;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void remPlayer(Player player){
        teamPlrs.remove(player);
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getLoses() {
        return loses;
    }

    public void setLoses(Integer loses) {
        this.loses = loses;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }
}
