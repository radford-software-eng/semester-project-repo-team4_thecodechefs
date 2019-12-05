package com.example.tenderrecipes;

import android.media.Image;
import android.widget.ImageView;

import java.util.ArrayList;

public class Recipe {

    private String name;
    //private String description;
    private String instructions;
    //private ImageView picture;
    private String ingredients;
    //private ArrayList<String> ingredients;
    private String difficulty;
    private String time;
    private String imgName;
    private int tempC;

    public Recipe(){

    }

    public Recipe(String n, /*String desc,*/ String inst,/* ImageView pict, */String ing, String diff, String t, int tC){
        name = n;
        //description = desc;
        instructions = inst;
        //picture = pict;
        ingredients = ing;
        difficulty = diff;
        time = t;
        tempC = tC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }*/

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    /*public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }*/

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTempC() {
        return tempC;
    }

    public void setTempC(int tempC) {
        this.tempC = tempC;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
