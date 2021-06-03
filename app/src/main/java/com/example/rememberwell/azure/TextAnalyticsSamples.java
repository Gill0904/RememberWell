package com.example.rememberwell.azure;
import android.widget.Toast;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.ai.textanalytics.models.*;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.example.rememberwell.BuildConfig;
import com.example.rememberwell.ui.fragment.registroFragment;

import java.security.Provider;


public class TextAnalyticsSamples{
    private static final String KEY = ""+ BuildConfig.AzureAppId;
    private static final String ENDPOINT = "https://textanalyticssw.cognitiveservices.azure.com/";


    public static void main(String[] args) {
        //You will create these methods later in the quickstart.
        TextAnalyticsClient client = authenticateClient(KEY, ENDPOINT);
        sentimentAnalysisExample(client);
    }
    public static void main() {
        //You will create these methods later in the quickstart.
        TextAnalyticsClient client = authenticateClient(KEY, ENDPOINT);
        sentimentAnalysisExample(client);
    }

    static TextAnalyticsClient authenticateClient(String key, String endpoint) {
        return new TextAnalyticsClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();
    }
    public static String sentimiento ="";

    static void sentimentAnalysisExample(TextAnalyticsClient client)
    {
        // The text that need be analyzed.
        String text = registroFragment.texto;
        System.out.println(text);
        DocumentSentiment documentSentiment = client.analyzeSentiment(text);
        sentimiento=""+documentSentiment.getSentiment();
        System.out.println(""+sentimiento);
    }

}

