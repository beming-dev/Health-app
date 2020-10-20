package org.techtown.realapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SaveExercise {
    public void saveData(Context context, ArrayList exercise, String key) {
        SharedPreferences prefForEx = context.getSharedPreferences(key, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefForEx.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exercise);
        editor.putString(Constants.EX_SHP_DATA_KEY, json);
        editor.apply();
    }

    public ArrayList<Ex> loadDataEx(Context context, String key) {
        SharedPreferences prefForEx = context.getSharedPreferences(key, context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefForEx.getString(Constants.EX_SHP_DATA_KEY, "");
        Type type = new TypeToken<ArrayList<Ex>>(){}.getType();
        return gson.fromJson(json, type);
    }

    public ArrayList<ExRecord> loadDataExRecord(Context context, String key) {
        SharedPreferences prefForEx = context.getSharedPreferences(key, context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefForEx.getString(Constants.EX_SHP_DATA_KEY, "");
        Type type = new TypeToken<ArrayList<ExRecord>>(){}.getType();
        return gson.fromJson(json, type);
    }
}
