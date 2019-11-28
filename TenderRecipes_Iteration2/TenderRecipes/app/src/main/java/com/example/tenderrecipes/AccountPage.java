package com.example.tenderrecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AccountPage extends AppCompatActivity {

    TextView userT;
    ImageView upArrow;
    LinearLayout recipeListLayout;
    ScrollView recipeScroll;

    String menuOption;

    ArrayList<CardView> recipeCards = new ArrayList<>();

    FirebaseDatabase database;
    DatabaseReference recipeDataRef;

    ArrayList<Integer> savedRecipes;
    Spinner sortMenu;

    Intent detailIntent;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);

        userT = findViewById(R.id.usernameT);
        recipeListLayout = findViewById(R.id.recipeList);
        recipeScroll = findViewById(R.id.recipeScroll);
        upArrow = findViewById(R.id.upArrow);

        sortMenu = findViewById(R.id.sortMenu);

        database = FirebaseDatabase.getInstance();
        recipeDataRef = database.getReference("Recipes");

        detailIntent = new Intent(AccountPage.this, RecipeDetail.class);

        Intent intent = getIntent();
        final Account account = intent.getParcelableExtra("account");

        savedRecipes = intent.getIntegerArrayListExtra("accList");

        userT.setText("Welcome "+account.getUsername());

        String[] sortMenuOptions = new String[]{"All","Breakfast","Lunch","Dinner"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sortMenuOptions);
        sortMenu.setAdapter(adapter);

        sortMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recipeListLayout.removeAllViews();
                menuOption = (String)parent.getItemAtPosition(position);
                loadRecipes();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        upArrow.setOnTouchListener(new OnSwipeTouchListener(AccountPage.this){
            public void onSwipeTop() {
                Toast.makeText(getApplicationContext(), "Swipe up", Toast.LENGTH_SHORT).show();
                finish();
            }

        });

        if(!savedRecipes.isEmpty()){

            loadRecipes();

        }else{

            Log.d("myTag", "Empty");

        }



    }

    public void loadRecipes(){
        recipeDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(int i = 0; i < savedRecipes.size(); i++){
                    for(DataSnapshot recipeSnapshot : dataSnapshot.getChildren()){
                        final Recipe re = recipeSnapshot.getValue(Recipe.class);
                        Log.d("myTag", ""+re.getMealTime());
                        if((re.getTempC() == savedRecipes.get(i) && re.getMealTime().equals(menuOption))
                                || (re.getTempC() == savedRecipes.get(i) && menuOption == "All")){
                            drawRecipeCard(re);
                        }

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void drawRecipeCard(final Recipe re){

        final CardView recipeCard = new CardView(getApplicationContext());
        final TextView nameText = new TextView(getApplicationContext());
        final TextView diff = new TextView(getApplicationContext());
        final ImageView img = new ImageView(getApplicationContext());

        nameText.setText(re.getName());
        nameText.setPadding(0,90,0,0);
        nameText.setTypeface(null, Typeface.BOLD);
        nameText.setTextColor(Color.BLACK);

        /*diff.setText(re.getDifficulty());
        diff.setPadding(1250, 90, 0, 0);
        diff.setTypeface(null, Typeface.BOLD);
        diff.setTextColor(Color.BLACK);*/

        img.setImageResource(AccountPage.this.getResources().getIdentifier(re.getImgName(),
                "drawable", AccountPage.this.getPackageName()));
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);

        recipeCard.addView(img);
        recipeCard.addView(nameText);
        recipeCard.addView(diff);
        recipeCard.setClickable(true);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150);
        LinearLayout.LayoutParams spacePar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 20);
        recipeCard.setLayoutParams(params);
        Space space = new Space(getApplicationContext());
        space.setLayoutParams(spacePar);

        recipeListLayout.addView(recipeCard);
        recipeListLayout.addView(space);

        recipeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailIntent.putExtra("recipeNum", re.getTempC());
                startActivity(detailIntent);

            }
        });

    }
}
