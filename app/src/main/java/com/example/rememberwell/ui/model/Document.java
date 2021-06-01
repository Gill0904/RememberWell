package com.example.rememberwell.ui.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Document {

    @SerializedName("documents")
    @Expose
    private List<Document__1> documents = null;
    @SerializedName("errors")
    @Expose
    private List<Object> errors = null;
    @SerializedName("modelVersion")
    @Expose
    private String modelVersion;

    public List<Document__1> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document__1> documents) {
        this.documents = documents;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

}