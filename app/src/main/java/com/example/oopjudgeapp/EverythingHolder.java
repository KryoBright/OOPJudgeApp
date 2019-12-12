package com.example.oopjudgeapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EverythingHolder {
    private static int matchId=0,tournamentId=0,teamId=0,playerId=0,judgeId=0,trainerId=0;
    private static List<Team> allTeams=new ArrayList<>();
    private static List<Tournament> allTournaments=new ArrayList<>();
    private static List<Match> allMatches=new ArrayList<>();
    private static List<Player> allPlayers=new ArrayList<>();
    private static List<Judge> allJudges=new ArrayList<>();
    private static List<Trainer> allTrainers=new ArrayList<>();


    public static List<Team> getAllTeams()
    {
        return Collections.unmodifiableList(allTeams);
    }


    public static List<Tournament> getAllTournaments()
    {
        return Collections.unmodifiableList(allTournaments);
    }


    public static List<Match> getAllMatches()
    {
        return Collections.unmodifiableList(allMatches);
    }


    public static List<Player> getAllPlayers()
    {
        return Collections.unmodifiableList(allPlayers);
    }


    public static List<Judge> getAllJudges()
    {
        return Collections.unmodifiableList(allJudges);
    }


    public static List<Trainer> getAllTrainers()
    {
        return Collections.unmodifiableList(allTrainers);
    }

    public static void addTeam(Team team)
    {
        if ((teamId>team.getId())&&(team.getId()!=-1))
        {
            allTeams.set(team.getId(),team);
        }
        else {
        team.setId(teamId);
        teamId++;
        allTeams.add(team);
        }
    }

    public static void addTournament(Tournament tournament) {
        if ((tournamentId>tournament.getId())&&(tournament.getId()!=-1))
        {
            allTournaments.set(tournament.getId(),tournament);
        }
        else {
        tournament.setId(tournamentId);
        tournamentId++;
        allTournaments.add(tournament);
    }
    }

    public static boolean addMatch(Match match)
    {if (((matchId>match.getId())&&(match.getId()!=-1)))
    {
        allMatches.set(match.getId(),match);
        return false;
    }
    else {
        match.setId(matchId);
        matchId++;
        allMatches.add(match);
        return true;
    }
    }

    public static void addPlayer(Player player){
        if (((playerId>player.getId())&&(player.getId()!=-1)))
        {
            allPlayers.set(player.getId(),player);
        }
        else {
            player.setId(playerId);
            playerId++;
            allPlayers.add(player);
        }
    }

    public static void addJudge(Judge judge){
        if (allJudges.contains(judge))
        {
            allJudges.set(judge.getId(),judge);
        }
        else {
            judge.setId(judgeId);
            judgeId++;
            allJudges.add(judge);
        }
    }

    public static void addTrainer(Trainer trainer){
        if (((trainerId>trainer.getId())&&(trainer.getId()!=-1)))
        {
            allTrainers.set(trainer.getId(),trainer);
        }
        else {
            trainer.setId(trainerId);
            trainerId++;
            allTrainers.add(trainer);
        }
    }

    public static List<String> getTrainersAsString()
    {
        List<String> returning=new ArrayList<>(trainerId);
        int i=0;
        while (i<trainerId)
        {
            returning.add(i,allTrainers.get(i).getName());
            i++;
        }
        return returning;
    }
}
