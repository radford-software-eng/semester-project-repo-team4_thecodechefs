package com.example.tenderrecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    int counter = 0;
    Context mContext;

    TextView nameT;
    TextView timeT;
    TextView diffT;
    TextView swipeDetect;

    ImageView recImg;

    FirebaseDatabase database;
    DatabaseReference recipeDataRef;

    ArrayList<Recipe> recipes = new ArrayList<>();

    Account currAccount;
    Intent detailIntent;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detailIntent = new Intent(MainActivity.this, RecipeDetail.class);

        //Gets the database
        database = FirebaseDatabase.getInstance();
        //References the Recipes section
        recipeDataRef = database.getReference("Recipes");

        Intent logInt = getIntent();
        Bundle extras = logInt.getExtras();
        currAccount = new Account(extras.getString("accName"), extras.getString("accPass"));

        recImg = findViewById(R.id.recipeImg);
        recImg.setScaleType(ImageView.ScaleType.CENTER_CROP);

        nameT = findViewById(R.id.nameText);
        nameT.setPadding(20,0,0,0);
        timeT = findViewById(R.id.timeText);
        timeT.setPadding(20,0,0,0);
        diffT = findViewById(R.id.diffText);
        diffT.setPadding(20,0,0,0);
        swipeDetect = findViewById(R.id.swipeDetect);

        //Check this method Chris
        addRecipes();

        swipeDetect.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){
            public void onSwipeTop() {
                Toast.makeText(getApplicationContext(), "Swipe up", Toast.LENGTH_SHORT).show();
                detailIntent.putExtra("recipeNum", recipes.get(counter).getTempC());
                startActivity(detailIntent);
            }
            public void onSwipeRight() {
                Toast.makeText(getApplicationContext(), "Recipe saved", Toast.LENGTH_SHORT).show();
                save(currAccount, recipes.get(counter).getTempC());
                recipes.remove(counter);
                Random randInt = new Random();
                int randCount = counter;
                while(randCount == counter){
                    counter = randInt.nextInt(recipes.size());
                }

                setCurrentRecipe(recipes, nameT, timeT, diffT, counter);
                Log.d("mytag", "Counter" + counter);

                recImg.setImageResource(MainActivity.this.getResources().getIdentifier(recipes.get(counter).getImgName(),
                        "drawable", MainActivity.this.getPackageName()));


            }
            public void onSwipeLeft() {
                Toast.makeText(getApplicationContext(), "Recipe Skipped", Toast.LENGTH_SHORT).show();
                Random randInt = new Random();
                int randCount = counter;
                while(randCount == counter){
                    counter = randInt.nextInt(recipes.size());
                }

                setCurrentRecipe(recipes, nameT, timeT, diffT, counter);
                Log.d("mytag", "" + counter);

                recImg.setImageResource(MainActivity.this.getResources().getIdentifier(recipes.get(counter).getImgName(),
                        "drawable", MainActivity.this.getPackageName()));
            }
            public void onSwipeBottom() {
                Toast.makeText(getApplicationContext(), "Swipe down", Toast.LENGTH_SHORT).show();
                accountPage(currAccount);
            }
        });

    }

    public void save(Account currAccount, Integer rNum){

        if(!currAccount.contains(rNum)) {
            currAccount.addRecipe(rNum);
        }else{
            Toast.makeText(MainActivity.this, "This recipe is already saved", Toast.LENGTH_SHORT).show();
        }


    }

    public void accountPage(Account current){

        Intent i = new Intent(MainActivity.this, AccountPage.class);
        i.putExtra("account", current);
        i.putIntegerArrayListExtra("accList", current.getSavedRecipes());
        //i.putParcelableArrayListExtra("recList", current.getSavedRecipes());
        startActivity(i);

    }

    public void setCurrentRecipe(ArrayList<Recipe> recipes, TextView nameT, TextView timeT, TextView diffT, int counter){

        Recipe currRec = recipes.get(counter);
        nameT.setText(currRec.getName());
        timeT.setText("Time: " + currRec.getTime());
        diffT.setText("Difficulty: " + currRec.getDifficulty());
        //String ing = currRec.getIngredients().replace("_n","\n");
        //ingrT.setText("Ingredients: \n" + ing);

    }


    public void addRecipes(){

        //To get data from the database add a ValueEventListener, adding it will
        //automatically create the onDataChange and onCancelled methods
        recipeDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            //Use this to read in the recipes(or accounts for you)
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot recipeSnapshot : dataSnapshot.getChildren()){

                    Recipe re = recipeSnapshot.getValue(Recipe.class);
                    recipes.add(re);

                }

                //You shouldn't need anything like this, i'm just setting the default recipe that is displayed
                setCurrentRecipe(recipes, nameT, timeT, diffT, 0);
                recImg.setImageResource(MainActivity.this.getResources().getIdentifier(recipes.get(counter).getImgName(),
                        "drawable", MainActivity.this.getPackageName()));
                Log.d("myTag", ""+counter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
