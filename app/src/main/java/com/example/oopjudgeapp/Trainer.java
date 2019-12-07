package com.example.oopjudgeapp;

public class Trainer extends Person {

    private Team team;

    public Trainer(String personName) {
        super(personName);
    }

    Team getTeam() {
        return team;
    }

    void setTeam(Team team) {
        this.team = team;
    }
}
