package com.example.tenderrecipes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Account implements Parcelable {

    private String username;
    private String password;

    private ArrayList<Integer> savedRecipes;

    public Account(){
        savedRecipes = new ArrayList<>();
    }

    public Account(String u, String p){
        username = u;
        password = p;
        savedRecipes = new ArrayList<>();
    }


    public void addRecipe(int r){
        savedRecipes.add(r);
    }

    public boolean contains(int r){
        for(int i = 0; i < savedRecipes.size(); i++){
            if(savedRecipes.get(i) == r){
                return true;
            }
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Integer> getSavedRecipes() {
        return savedRecipes;
    }

    public void setSavedRecipes(ArrayList<Integer> savedRecipes) {
        this.savedRecipes = savedRecipes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(username);
        dest.writeString(password);
        //dest.writeList(savedRecipes);

    }

    public static final Parcelable.Creator<Account> CREATOR = new Parcelable.Creator<Account>() {
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    public Account(Parcel in) {
        //mData = in.readInt();
        username = in.readString();
        password = in.readString();
        savedRecipes = in.readArrayList(Recipe.class.getClassLoader());
    }
}
