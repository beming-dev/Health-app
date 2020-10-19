package org.techtown.realapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SaveExercise {
    public void SaveExerciseData(Context context, ArrayList<Ex> exercise, String key) {
        SharedPreferences prefForEx = context.getSharedPreferences(key, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefForEx.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exercise);
        editor.putString(Constants.EX_SHP_DATA_KEY, json);
        editor.apply();
    }

    public ArrayList<Ex> ReadExerciseData(Context context, String key) {
        SharedPreferences prefForEx = context.getSharedPreferences(key, context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefForEx.getString(Constants.EX_SHP_DATA_KEY, "");
        Type type = new TypeToken<ArrayList<Ex>>(){}.getType();
        ArrayList<Ex> arrayList = gson.fromJson(json, type);

        return arrayList;
    }

    public void SaveExerciseCompData(Context context, ArrayList<CompleteEx> exercise, String key) {
        SharedPreferences prefForEx = context.getSharedPreferences(key, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefForEx.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exercise);
        editor.putString(Constants.EX_SHP_DATA_KEY, json);
        editor.apply();
    }

    public ArrayList<CompleteEx> ReadExercisCompData(Context context, String key) {
        SharedPreferences prefForEx = context.getSharedPreferences(key, context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefForEx.getString(Constants.EX_SHP_DATA_KEY, "");
        Type type = new TypeToken<ArrayList<CompleteEx>>(){}.getType();
        ArrayList<CompleteEx> arrayList = gson.fromJson(json, type);

        return arrayList;
    }
}
