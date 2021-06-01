package com.example.rememberwell.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sentence {

    @SerializedName("sentiment")
    @Expose
    private String sentiment;
    @SerializedName("confidenceScores")
    @Expose
    private ConfidenceScores__1 confidenceScores;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("text")
    @Expose
    private String text;

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public ConfidenceScores__1 getConfidenceScores() {
        return confidenceScores;
    }

    public void setConfidenceScores(ConfidenceScores__1 confidenceScores) {
        this.confidenceScores = confidenceScores;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}