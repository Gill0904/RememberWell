package com.example.rememberwell.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfidenceScores__1 {

    @SerializedName("positive")
    @Expose
    private Double positive;
    @SerializedName("neutral")
    @Expose
    private Double neutral;
    @SerializedName("negative")
    @Expose
    private Double negative;

    public Double getPositive() {
        return positive;
    }

    public void setPositive(Double positive) {
        this.positive = positive;
    }

    public Double getNeutral() {
        return neutral;
    }

    public void setNeutral(Double neutral) {
        this.neutral = neutral;
    }

    public Double getNegative() {
        return negative;
    }

    public void setNegative(Double negative) {
        this.negative = negative;
    }

}