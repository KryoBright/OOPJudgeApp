package com.example.oopjudgeapp;

public class Player extends Person {
    public Player(String personName) {
        super(personName);
        EverythingHolder.addPlayer(this);
    }
}
