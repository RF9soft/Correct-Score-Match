package com.correct.score.model;

public class DataModel  {

    public String league_name;
    public String  team_1;
    public String  team_2;
    public String tips_name;
    public String  odds;
    public String  vs;
    public String   date;

    public DataModel() {
    }

    public String getLeague_name() {
        return league_name;
    }

    public String getTeam_1() {
        return team_1;
    }

    public String getTeam_2() {
        return team_2;
    }

    public String getTips_name() {
        return tips_name;
    }

    public String getOdds() {
        return odds;
    }

    public String getVs() {
        return vs;
    }

    public String getDate() {
        return date;
    }

    public DataModel(String league_name, String team_1, String team_2, String tips_name, String odds, String vs, String date) {
        this.league_name = league_name;
        this.team_1 = team_1;
        this.team_2 = team_2;
        this.tips_name = tips_name;
        this.odds = odds;
        this.vs = vs;
        this.date = date;

    }
}

