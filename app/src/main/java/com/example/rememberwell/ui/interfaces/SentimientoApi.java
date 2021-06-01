package com.example.rememberwell.ui.interfaces;


import com.example.rememberwell.ui.model.Document;
import com.example.rememberwell.ui.model.Sentimiento;
import com.fasterxml.jackson.databind.node.POJONode;
import com.google.gson.JsonObject;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.fasterxml.jackson.databind.type.LogicalType.POJO;

public interface SentimientoApi {
    @Headers({"Accept: application/json","Ocp-Apim-Subscription-Key: " + "b9fa3d12826d4c2da16f94d1b2f826e5",
            "Content-Type: application/json;charset=UTF-8"})
    @POST(".")
    Call<Document> savePost(@Body HashMap body);
}
