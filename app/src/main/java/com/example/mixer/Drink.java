package com.example.mixer;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Parcel // To make Drink object parceable
// Encapsulates what makes up a movie in the app
public class Drink {
    int drinkID;
    String drinkName;
    String drinkIBA;
    String drinkCategory;
    String posterPath;
    String drinkInstructions;
    List <String> drinkIngredients;

    // Strings to help parse ingredients
    String ingredient = "strIngredient";
    String measure = "strMeasure";
    int LEN_INGREDIENTS =15;

    // empty constructor needed by Parcelor Library
    public Drink(){};

    public Drink(JSONObject jsonObject) throws JSONException {
        drinkID = jsonObject.getInt("idDrink");
        drinkName = jsonObject.getString("strDrink");
        drinkIBA = jsonObject.getString("strAlcoholic");
        drinkCategory = jsonObject.getString("strCategory");
        drinkInstructions = jsonObject.getString("strInstructions");
        posterPath = jsonObject.getString("strDrinkThumb");
        drinkIngredients = new ArrayList<>();


        // Extracts ingredients and measurements and stores it in the list
        for (int i=1; i < LEN_INGREDIENTS; i++) {
            String ingredientKey = ingredient + i;
            String measurementKey = measure + i;

            String testIfNull = jsonObject.getString(ingredientKey);
            testIfNull = testIfNull.replaceAll("\\s+","");

            // Parse ingredient and measurement from JSON
            if (testIfNull == "null" || testIfNull.isEmpty()){
            }
            else {
                String tempIngredient = jsonObject.getString(ingredientKey) + ": " + jsonObject.getString(measurementKey);
                drinkIngredients.add(tempIngredient);
            }
        }
        Log.d("Ingredients", String.valueOf(drinkIngredients));
    }

    public static Drink fromJsonArray(JSONArray drinkJsonArray) throws JSONException {
        return new Drink(drinkJsonArray.getJSONObject(0));
    }

    // getters to extract posterPath, title, and overview from Movie objects

    public int getDrinkID() { return drinkID; }

    public String getPosterPath() { return String.format(posterPath); }

    public String getDrinkName() {
        return drinkName;
    }

    public String getDrinkIBA() {
        return drinkIBA;
    }

    public String getDrinkCategory() {
        return drinkCategory;
    }

    public String getDrinkInstructions() {
        return drinkInstructions;
    }

    public List<String> getIngredients() {
        return drinkIngredients;
    }



}