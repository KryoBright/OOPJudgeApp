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
        team.setId(teamId);
        teamId++;
        allTeams.add(team);
    }

    public static void addTournament(Tournament tournament)
    {
        tournament.setId(tournamentId);
        tournamentId++;
        allTournaments.add(tournament);
    }

    public static void addMatch(Match match)
    {
        match.setId(matchId);
        matchId++;
        allMatches.add(match);
    }

    public static void addPlayer(Player player){
        player.setId(playerId);
        playerId++;
        allPlayers.add(player);
    }

    public static void addJudge(Judge judge){
        judge.setId(judgeId);
        judgeId++;
        allJudges.add(judge);
    }

    public static void addTrainer(Trainer trainer){
        trainer.setId(trainerId);
        trainerId++;
        allTrainers.add(trainer);
    }
}
