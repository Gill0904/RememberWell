package com.example.rememberwell.ui.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Document__1 {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("sentiment")
    @Expose
    private String sentiment;
    @SerializedName("confidenceScores")
    @Expose
    private ConfidenceScores confidenceScores;
    @SerializedName("sentences")
    @Expose
    private List<Sentence> sentences = null;
    @SerializedName("warnings")
    @Expose
    private List<Object> warnings = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public ConfidenceScores getConfidenceScores() {
        return confidenceScores;
    }

    public void setConfidenceScores(ConfidenceScores confidenceScores) {
        this.confidenceScores = confidenceScores;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public List<Object> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<Object> warnings) {
        this.warnings = warnings;
    }

}