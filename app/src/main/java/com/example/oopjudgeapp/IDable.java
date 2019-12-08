package com.example.oopjudgeapp;

public abstract class IDable {
    private int id=-1;
    public void setId(Integer id) {
        if (this.id==-1) this.id = id;
    }
}
