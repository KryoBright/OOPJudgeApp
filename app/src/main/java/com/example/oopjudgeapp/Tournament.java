package com.example.oopjudgeapp;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tournament {
    private List<Match> matches=new ArrayList<>();
    private String name;
    private String desc;

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

}
