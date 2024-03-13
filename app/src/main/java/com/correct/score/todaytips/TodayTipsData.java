package com.correct.score.todaytips;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodayTipsData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tips_name")
    @Expose
    private String tipsName;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("league_name")
    @Expose
    private String leagueName;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("team_one")
    @Expose
    private String teamOne;
    @SerializedName("team_one_image")
    @Expose
    private String teamOneImage;
    @SerializedName("team_two")
    @Expose
    private String teamTwo;
    @SerializedName("team_two_image")
    @Expose
    private String teamTwoImage;
    @SerializedName("odds_value")
    @Expose
    private String oddsValue;
    @SerializedName("team_one_score")
    @Expose
    private String teamOneScore;
    @SerializedName("team_two_score")
    @Expose
    private String teamTwoScore;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipsName() {
        return tipsName;
    }

    public void setTipsName(String tipsName) {
        this.tipsName = tipsName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(String teamOne) {
        this.teamOne = teamOne;
    }

    public String getTeamOneImage() {
        return teamOneImage;
    }

    public void setTeamOneImage(String teamOneImage) {
        this.teamOneImage = teamOneImage;
    }

    public String getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(String teamTwo) {
        this.teamTwo = teamTwo;
    }

    public String getTeamTwoImage() {
        return teamTwoImage;
    }

    public void setTeamTwoImage(String teamTwoImage) {
        this.teamTwoImage = teamTwoImage;
    }

    public String getOddsValue() {
        return oddsValue;
    }

    public void setOddsValue(String oddsValue) {
        this.oddsValue = oddsValue;
    }

    public String getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(String teamOneScore) {
        this.teamOneScore = teamOneScore;
    }

    public String getTeamTwoScore() {
        return teamTwoScore;
    }

    public void setTeamTwoScore(String teamTwoScore) {
        this.teamTwoScore = teamTwoScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
