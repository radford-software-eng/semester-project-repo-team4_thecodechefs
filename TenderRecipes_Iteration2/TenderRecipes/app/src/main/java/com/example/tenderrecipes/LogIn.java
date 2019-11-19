package com.example.tenderrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.Array;
import java.util.ArrayList;

public class LogIn extends AppCompatActivity {

    private EditText password;
    private EditText username;
    private Button submit;
    private Button skip;
    private Button register;

    ArrayList<Account> accountList = new ArrayList<Account>();
    Account mod1 = new Account("Patrick", "USA");
    Account mod2 = new Account("JT", "test");
    Account mod3 = new Account("Chris", "beard");
    Account mod4 = new Account("Bryan","Y");
    Account mod5 = new Account("Jasmine", "lea");
    Account mod6 = new Account("Paul", "paul1");

    Intent logInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        password = findViewById(R.id.passwordField);
        username = findViewById(R.id.usernameField);
        submit = findViewById(R.id.submitBut);
        skip = findViewById(R.id.skipB);
        //register = findViewById(R.id.registerBut);

        accountList.add(mod1);
        accountList.add(mod2);
        accountList.add(mod3);
        accountList.add(mod4);
        accountList.add(mod5);

        logInt = new Intent(LogIn.this, MainActivity.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                boolean isFound = false;


                if(user.equals("admin") && pass.equals("password")){
                    startActivity(new Intent(LogIn.this, MainActivity.class));
                }else {
                    for(int i = 0; i < accountList.size(); i++)
                        if (accountList.get(i).getUsername().equals(user) && accountList.get(i).getPassword().equals(pass)) {

                            logInt.putExtra("accName", user);
                            logInt.putExtra("accPass", pass);
                            startActivity(logInt);
                            //startActivity(new Intent(LogIn.this, MainActivity.class));
                            isFound = true;

                        }
                }
                if(isFound == false){
                    Toast.makeText(LogIn.this, "The username or password was incorrect", Toast.LENGTH_SHORT).show();

                }
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInt.putExtra("accName", "Admin");
                logInt.putExtra("accPass", "Password");
                startActivity(logInt);
            }
        });


    }
}
