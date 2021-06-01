package com.example.rememberwell.azure;

import com.example.rememberwell.ui.interfaces.SentimientoApi;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://textanalyticssw.cognitiveservices.azure.com/text/analytics/v3.1-preview.5/sentiment/";

    public static SentimientoApi getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(SentimientoApi.class);
    }
}