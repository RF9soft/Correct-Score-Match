package com.correct.score.footballtips.details;

import com.correct.score.footballtips.Tip;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DetailsData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("tips")
    @Expose
    private ArrayList<Tip> tips;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<Tip> getTips() {
        return tips;
    }

    public void setTips(ArrayList<Tip> tips) {
        this.tips = tips;
    }
}
