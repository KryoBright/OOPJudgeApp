package com.example.oopjudgeapp;

import android.text.method.DateTimeKeyListener;

import java.text.DateFormat;
import java.util.Date;

public class Match {
    private Judge judge;
    private Team team1,team2;
    private Integer leftScore,rightScore;
    private Date matchDate;

    public void setJudge(Judge judge) {
        this.judge = judge;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Judge getJudge() {
        return judge;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public Integer getLeftScore() {
        return leftScore;
    }

    public Integer getRightScore() {
        return rightScore;
    }

    public void setLeftScore(Integer leftScore) {
        this.leftScore = leftScore;
    }

    public void setRightScore(Integer rightScore) {
        this.rightScore = rightScore;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchDateAsString()
    {
        return DateFormat.getDateInstance().format(matchDate);
    }
}
