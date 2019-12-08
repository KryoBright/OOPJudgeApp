package com.example.oopjudgeapp;

public class Judge extends Person {
    public Judge(String personName) {
        super(personName);
        EverythingHolder.addJudge(this);
    }
}
