package com.example.oopjudgeapp;

public abstract class Person extends IDable {
    private
    int wins,loses;
    private
    String name;

    public Person(String personName) {
        wins=0;
        loses=0;
        name=personName;
    }

    public double getEffectivity(){
        double effectivity = wins / (loses + wins + 1.0);
        return effectivity;
    }

    public void setWinsLoses(int w,int l){
        wins=w;
        loses=l;
    }

    public int getLoses() {
        return loses;
    }

    public int getWins() {
        return wins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
