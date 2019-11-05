package com.example.tenderrecipes;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    int counter = 0;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Recipe> recipes = new ArrayList<>();

        final ImageView recImg = findViewById(R.id.recipeImg);
        recImg.setImageResource(R.drawable.scrambled_eggs);

        final TextView nameT = findViewById(R.id.nameText);
        final TextView timeT = findViewById(R.id.timeText);
        final TextView ingrT = findViewById(R.id.ingredientsText);

        Button nextButton = findViewById(R.id.nextButton);
        //Button instButton = findViewById(R.id.instButton);

        createRecipes(recipes);

        setCurrentRecipe(recipes, nameT, timeT, ingrT, 0);

        nextButton.setOnClickListener(new View.OnClickListener() {
            //Recipe firstRec = recipes.get(0);
            @Override
            public void onClick(View v) {

                Random randInt = new Random();
                int randCount = counter;
                while(randCount == counter){
                    counter = randInt.nextInt(recipes.size());
                }

                /*if((counter+1) >= recipes.size()){
                    counter = 0;
                }else counter = counter + 1;*/
                setCurrentRecipe(recipes, nameT, timeT, ingrT, counter);
                Log.d("mytag", "" + counter);

                recImg.setImageResource(MainActivity.this.getResources().getIdentifier(recipes.get(counter).getImgName(),
                        "drawable", MainActivity.this.getPackageName()));

            }
        });



        /*instButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Instructions.class));

            }
        });*/

    }

    public void setCurrentRecipe(ArrayList<Recipe> recipes, TextView nameT, TextView timeT, TextView ingrT, int counter){

        //int countTemp = counter%2;

        Recipe currRec = recipes.get(counter);
        nameT.setText(currRec.getName());
        timeT.setText("Time: " + currRec.getTime());
        ingrT.setText("Ingredients: \n" + currRec.getIngredients());

        //counter = counter++;
    }

    public void createRecipes(ArrayList<Recipe> recipes){

        String n1 = "Scrambled Eggs";
        String n2 = "French Toast";
        String n3 = "Fettuccine Alfredo";
        String n4 = "Grilled Cheese";
        String n5 = "Chicken Noodle Soup";

        String inst1 =
                "Step 1: BEAT eggs, milk, salt, and pepper in medium bowl until blended.\n" +
                "Step 2: HEAT butter in large nonstick skillet over medium heat until hot.\n" +
                "          POUR IN egg mixture. As eggs begin to set, GENTLY PULL the eggs across\n" +
                "          the pan with a spatula, forming large soft curds.\n" +
                "Step 3: CONTINUE cooking – pulling, lifting and folding eggs – until thickened\n" +
                        "  and no visible liquid egg remains. Do not stir constantly. REMOVE from heat.\n" +
                        "  SERVE immediately.";

        String inst2 =
                        "Step 1: Beat egg, vanilla and cinnamon in shallow dish. Stir in milk.\n" +
                        "Step 2: Dip bread in egg mixture, turning to coat both sides evenly." +
                        "Step 3: Cook bread slices on lightly greased nonstick griddle or skillet\n" +
                        " on medium heat until browned on both sides. Serve with Easy Spiced Syrup\n" +
                                "(recipe follows), if desired.\n" +
                                "Step 4: Easy Spiced Syrup: Add 1 teaspoon McCormick® Pure Vanilla Extract\n" +
                                " and 1/4 teaspoon McCormick® Ground Cinnamon to 1 cup pancake syrup; stir well to mix.\n" +
                                " Serve warm, if desired.";

        String inst3 =
                "Step 1: In a large pot, heat water over high heat until boiling. Add salt to season the water.\n" +
                        " Once it is boiling, add fettuccine and cook according to package instructions."+
                "Step 2: In a large skillet or pan, heat butter over medium heat. Add minced garlic and\n" +
                        " cook for 1 to 2 minutes. Stir in heavy cream. " +
                "Step 3: Let heavy cream reduce and cook for 5 to 8 minutes. Add half of the parmesan\n" +
                        " cheese to the mixture and whisk well until smooth. Keep over heat and whisk\n" +
                        " well until cheese is melted." +
                "Step 4: Save some pasta water. The pasta water is full of flavor and can be\n" +
                        " used to thin out the sauce." +
                "Step 5: Toss alfredo sauce with fettuccine pasta and add half of the parmesan cheese.\n" +
                        " Once it is tossed, garnish with the remaining parmesan cheese.\n" +
                        " Add a little pasta water if it needs to be thinned out." +
                "Step 6: Garnish with Italian parsley, if so desired.";

        String inst4 =
                "Step 1: Preheat skillet over medium heat. Generously butter one side of a slice of bread.\n" +
                "Step 2: Place bread butter-side-down onto skillet bottom and add 1 slice of cheese.\n" +
                    " Butter a second slice of bread on one side and place butter-side-up on top of sandwich.\n" +
                "Step 3:  Grill until lightly browned and flip over; continue grilling until cheese is melted.\n" +
                        " Repeat with remaining 2 slices of bread, butter and slice of cheese.";

        String inst5 =
                "Step 1: In a large pot over medium heat, melt butter. " +
                "Step 2: Cook onion and celery in butter until just tender, 5 minutes." +
                "Step 3: Pour in chicken and vegetable broths and stir in chicken, noodles,\n" +
                        " carrots, basil, oregano, salt and pepper." +
                "Step 4: Bring to a boil, then reduce heat and simmer 20 minutes before serving.";

        /*ArrayList<String> ing1 = new ArrayList<>();
        ing1.add("4 Eggs");
        ing1.add("1/4 Cup Milk");
        ing1.add("Salt and Pepper as desired");
        ing1.add("2 tsp. Butter");

        ArrayList<String> ing2 = new ArrayList<>();
        ing2.add("1 Egg");
        ing2.add("1 teaspoon Vanilla Extract");
        ing2.add("1/2 teaspoon Cinnamon(Ground)");
        ing2.add("4 Slices of bread");*/

        String ing1 =
                "4 Eggs\n" +
                "1/4 Cup Milk\n" +
                "Salt and Pepper as desired\n" +
                "2 tsp. Butter";

        String ing2 =
                "1 Egg\n" +
                "1 teaspoon Vanilla Extract\n" +
                "1/2 teaspoon Cinnamon(Ground)\n" +
                "4 Slices of bread";

        String ing3 =
                "1 lb Fettuccine Pasta\n" +
                "6 Tablespoons Butter\n" +
                "1 Garlic Clove (minced)\n" +
                "1 1/2 cups Heavy Cream\n" +
                "1/4 teaspoon Salt\n" +
                "1 1/4 cup Shredded Parmesan Cheese\n" +
                "1/4 teaspoon Pepper\n" +
                "2 Tablespoons Italian Parsley (optional)";

        String ing4 =
                "4 Slices white bread\n" +
                "2 Slices Cheddar cheese\n" +
                "3 tablespoons butter\n";

        String ing5 =
                "1 tablespoon butter\n" +
                "½ cup chopped onion\n" +
                "½ cup chopped celery\n" +
                "4 (14.5 ounce) cans chicken broth \n" +
                "1 (14.5 ounce) can vegetable broth\n" +
                "½ pound chopped cooked chicken breast\n" +
                "1 ½ cups egg noodles\n" +
                "1 cup sliced carrots\n" +
                "½ teaspoon dried basil\n" +
                "½ teaspoon dried oregano\n" +
                "salt and pepper to taste";

        String diff1 = "Easy";
        String diff2 = "Easy";
        String diff3 = "Easy";
        String diff4 = "Easy";
        String diff5 = "Easy";


        String time1 = "5 Min";
        String time2 = "10 Min";
        String time3 = "20 Min";
        String time4 = "20 Min";
        String time5 = "30 Min";

        Recipe r1 = new Recipe(n1, inst1, ing1, diff1, time1, 0);
        Recipe r2 = new Recipe(n2, inst2, ing2, diff2, time2, 1);
        Recipe r3 = new Recipe(n3, inst3, ing3, diff3, time3, 2);
        Recipe r4 = new Recipe(n4, inst4, ing4, diff4, time4, 3);
        Recipe r5 = new Recipe(n5, inst5, ing5, diff5, time5, 4);

        r1.setImgName("scrambled_eggs");
        r2.setImgName("french_toast");
        r3.setImgName("fettuccine_alfredo");
        r4.setImgName("grilled_cheese");
        r5.setImgName("chicken_noodle_soup");

        recipes.add(r1);
        recipes.add(r2);
        recipes.add(r3);
        recipes.add(r4);
        recipes.add(r5);

    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d("gestTag", "onDoubleTap: " + e.toString());
        final TextView nameT = findViewById(R.id.nameText);
        nameT.setText("Tapped");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("gestTag","onDown: " + e.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
