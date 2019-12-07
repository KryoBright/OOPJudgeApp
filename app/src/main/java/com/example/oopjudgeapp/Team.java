package com.example.oopjudgeapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private String name;
    private Trainer trainer=null;
    private List<Player> teamPlrs=new ArrayList<>();

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


}
